package persistence.patient.utilImpl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import persistence.admin.dao.PharmaInvoiceDAO;
import persistence.admin.daoImpl.PharmaInvoiceDAOImpl;
import persistence.admin.model.PharmaInvoice;
import persistence.common.paymentInterface.dao.PaymentInterfaceDAO;
import persistence.common.paymentInterface.daoImpl.PaymentInterfaceDAOImpl;
import persistence.doctor.dao.AppointmentDAO;
import persistence.doctor.dao.DoctorDAO;
import persistence.doctor.dao.PrescriptionDAO;
import persistence.doctor.daoImpl.AppointmentDAOImpl;
import persistence.doctor.daoImpl.DoctorDAOImpl;
import persistence.doctor.daoImpl.PrescriptionDAOImpl;
import persistence.doctor.model.Appointment;
import persistence.doctor.model.Prescription;
import persistence.patient.dao.ImmunizationBookingDAO;
import persistence.patient.dao.LabCheckBookingDAO;
import persistence.patient.daoImpl.ImmunizationBookingDAOImpl;
import persistence.patient.daoImpl.LabCheckBookingDAOImpl;
import persistence.patient.model.ImmunizationBooking;
import persistence.patient.model.Invoice;
import persistence.patient.model.LabCheckBooking;
import persistence.patient.model.Patient;
import persistence.patient.util.PatientInvoiceUtil;

/**
 * <pre>
 * This class provides abstraction to the database.
 * It fetches data from DAO objects and returns back to the 
 * presentation layer.
 * 
 * It fetches data 
 * 	1. for a patient.
 * 	2. Doctor's appointment table.
 * 	3. LabCheck appointments table.
 * 	4. Pharma supplies table.
 * 	5. Prescription table.
 * 	6. Doctor's table.
 * 	7. Payment table.
 * 
 * All the data in this file will be dynamic and will come
 * from DAO (Database). Therefore, this is not covered in tests 
 * except for getGenericInvoiceDetails which is covered in 
 * PatientInvoiceUtilImplTest.
 * </pre>
 * 
 * @author Gurleen Saluja
 *
 */
public class PatientInvoiceUtilImpl implements PatientInvoiceUtil {

	@Override
	public Invoice getGenericInvoiceDetails() {
		Invoice invoice = new Invoice();
		invoice.setPatientName(Patient.instance().getPatientName());
		invoice.setAddress(Patient.instance().getPatientAddress());
		invoice.setOriginalDatetime(new Timestamp(System.currentTimeMillis()));
		invoice.setAge(calculateAge());
		invoice.setContactNumber(String.valueOf(Patient.instance().getPatientContact()));
		invoice.setGender(Patient.instance().getPatientGender().trim().charAt(0));
		return invoice;
	}
	
	private int calculateAge() {
		LocalDate patientBirthdate = LocalDate.parse(Patient.instance().getPatientDob());
		LocalDate currentDate = LocalDate.now();
		return Period.between(patientBirthdate, currentDate).getYears();
	}

	@Override
	public Invoice generateAppointmentInvoice(String date, Invoice invoice) {
		AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
		List<Appointment> actualAppointmentList = new ArrayList<>();
		List<Appointment> appointmentsList = appointmentDAO.fetchAppointmentsForPatient();
		List<Integer> doctorIdList = new ArrayList<>();
		List<Integer> billingIdList = new ArrayList<>();
		for(Appointment appointment: appointmentsList) {
			if(appointment.getBookedForDate().toString().equals(date)) {
				doctorIdList.add(appointment.getDoctorId());
				actualAppointmentList.add(appointment);
				billingIdList.add(appointment.getBillingId());
			}
		}
		DoctorDAO doctorDAO = new DoctorDAOImpl();
		if(!doctorIdList.isEmpty()) {
			invoice.setDoctorDetail(doctorDAO.getDoctorNameById(doctorIdList));
		}
		if(!billingIdList.isEmpty()) {
			PaymentInterfaceDAO paymentDAO = new PaymentInterfaceDAOImpl();
			invoice.setPaymentMap(paymentDAO.getPaymentDetails(billingIdList));
		}
		if(!actualAppointmentList.isEmpty()) {
			invoice.setAppointmentList(actualAppointmentList);
		}
		return invoice;
	}

	@Override
	public Invoice generateLabCheckInvoice(String date, Invoice invoice) {
		List<Integer> billingIdList = new ArrayList<>();
		LabCheckBookingDAO labCheckBookingDAO = new LabCheckBookingDAOImpl();
		List<LabCheckBooking> labCheckBookingList = labCheckBookingDAO.getAllBookings();
		List<LabCheckBooking> actualLabCheckBookings = new ArrayList<>();
		List<Integer> healthCheckIdList = new ArrayList<>();
		for(LabCheckBooking labCheckBooking: labCheckBookingList) {
			if(labCheckBooking.getBookedForDate().toString().equals(date)) {
				actualLabCheckBookings.add(labCheckBooking);
				billingIdList.add(labCheckBooking.getBillingId());
				healthCheckIdList.add(labCheckBooking.getHealthcheckId());
			}
		}
		if(!actualLabCheckBookings.isEmpty()) {
			invoice.setLabCheckBookingList(actualLabCheckBookings);
		}
		if(!billingIdList.isEmpty()) {
			PaymentInterfaceDAO paymentDAO = new PaymentInterfaceDAOImpl();
			invoice.setPaymentMap(paymentDAO.getPaymentDetails(billingIdList));
		}
		if(!healthCheckIdList.isEmpty()) {
			invoice.setLabCheckMap(labCheckBookingDAO.getHealthChecks(healthCheckIdList));
		}
		return invoice;
	}

	@Override
	public Invoice generatePharmaInvoice(String date, Invoice invoice) {
		List<Integer> billingIdList = new ArrayList<>();
		PrescriptionDAO prescriptionDAO = new PrescriptionDAOImpl();
		List<Prescription> prescriptionList = prescriptionDAO.getPrescriptionByPatientId();
		List<String> medicineNameList = new ArrayList<>();
		for(Prescription prescription: prescriptionList) {
			if(prescription.getDate().toString().equals(date)) {
				invoice.setPrescriptionId(prescription.getPrescriptionId());
				invoice.setBillId(prescription.getBillingId());
				medicineNameList.add(prescription.getMedicineName());
				billingIdList.add(prescription.getBillingId());
			}
		}
		if(!medicineNameList.isEmpty()) {
			PharmaInvoiceDAO pharmaInvoiceDAO = new PharmaInvoiceDAOImpl();
			List<PharmaInvoice> pharmaInvoiceList = pharmaInvoiceDAO.getPharmaSupplies(medicineNameList);
			invoice.setPharmaInvoiceList(pharmaInvoiceList);
		}
		if(!billingIdList.isEmpty()) {
			PaymentInterfaceDAO paymentDAO = new PaymentInterfaceDAOImpl();
			invoice.setPaymentMap(paymentDAO.getPaymentDetails(billingIdList));
		}
		return invoice;
	}

	@Override
	public Invoice generateImmunizationInvoice(String date, Invoice invoice) {
		ImmunizationBookingDAO immunizationDAO = new ImmunizationBookingDAOImpl();
		List<Integer> vaccineIdList = new ArrayList<>();
		for(ImmunizationBooking booking: immunizationDAO.getVaccineIdByPatientId()) {
			if(booking.getBookedForDate().toString().equals(date)) {
				vaccineIdList.add(booking.getVaccineId());
			}
		}
		if(!vaccineIdList.isEmpty()) {
			Map<Integer, String> vaccineMap = immunizationDAO.getVaccineDetailById(vaccineIdList);
			invoice.setVaccineMap(vaccineMap);
		}
		return invoice;
	}
}

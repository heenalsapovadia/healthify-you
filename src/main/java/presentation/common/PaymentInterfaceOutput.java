package presentation.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentInterfaceOutput {
    private static class PaymentInterfaceOutputHelper {
        private static final PaymentInterfaceOutput instance = new PaymentInterfaceOutput();
    }

    public static PaymentInterfaceOutput getInstance() {
        return PaymentInterfaceOutput.PaymentInterfaceOutputHelper.instance;
    }

    public void displayOutput() {
        PrintToConsole consoleObj = PrintToConsole.getInstance();
        consoleObj.printHeader(ScreenTitles.paymentInterface);
        loadScreenOptions(consoleObj);
    }

    private List<String> getSelectionOptions() {
        List<String> selectionOptions = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int cardNumber;
        int expirtyDate;
        int cvvNumber;
        int checkoutAmount = 1000;
        String redeemVoucherAmount;
        String voucherId;
        System.out.println(ScreenFields.cardNumber);
        cardNumber = sc.nextInt();
        System.out.println(ScreenFields.expirydate);
        expirtyDate= sc.nextInt();
        System.out.println(ScreenFields.cvvNumber);
        cvvNumber = sc.nextInt();
        System.out.println(ScreenFields.checkoutAmount + checkoutAmount);
        System.out.println(ScreenFields.redeemVoucher);
        System.out.println(ScreenFields.voucherId);
        System.out.println(ScreenFields.exit);
        return selectionOptions;
    }

    private int loadScreenOptions(PrintToConsole consoleObj) {
        List<String> selectionOptions = getSelectionOptions();
        int sel = consoleObj.printSelection(selectionOptions);
        if(sel == 1) {
            //Reedem voucher
        }
        else if(sel == 2) {
            System.out.println(ScreenFields.logoutMessage);
            System.out.println(ScreenFields.applicationTerminationMessage);
            System.exit(0);
        }
        else {
            consoleObj.printError(CommonErrors.invalidSelection);
            sel = loadScreenOptions(consoleObj);
        }
        return sel;
    }
}

package org.example;

public class ValidateSaId {
    public static boolean isIdNumberValid(String idNumber) {
        // Must be 13 digits
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d{13}")) {
            return false;
        }

        // Parse date of birth (YYMMDD)
        String dob = idNumber.substring(0, 6);
        if (!isValidDate(dob)) {
            return false;
        }

        // Gender (SSSS): 0000-4999 = Female, 5000-9999 = Male
        int gender = Integer.parseInt(idNumber.substring(6, 10));
        if (gender < 0 || gender > 9999) {
            return false;
        }

        // Citizenship (C): 0 = citizen, 1 = permanent resident
        char citizenship = idNumber.charAt(10);
        if (citizenship != '0' && citizenship != '1') {
            return false;
        }

        // Luhn checksum (Z)
        return luhnCheck(idNumber);
    }

    private static boolean isValidDate(String dob) {
        if (dob.length() != 6) return false;
        int year = Integer.parseInt(dob.substring(0, 2));
        int month = Integer.parseInt(dob.substring(2, 4));
        int day = Integer.parseInt(dob.substring(4, 6));

        // Accept years 1900-2099 (not strictly enforced here)
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false;
        // Simple check for month/day validity
        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (month == 2 && day == 29) return true; // allow leap years for simplicity
        return day <= daysInMonth[month-1];
    }

    private static boolean luhnCheck(String idNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = idNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(idNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n = (n % 10) + 1;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}

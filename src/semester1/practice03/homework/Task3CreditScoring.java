package semester1.practice03.homework;

import java.util.Scanner;
public class Task3CreditScoring {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("       СИСТЕМА БАНКІВСЬКОГО СКОРИНГУ");
        System.out.println("==============================================");

        System.out.print("Введіть вік позичальника: ");
        int age = scanner.nextInt();

        System.out.print("Введіть офіційний місячний дохід (грн): ");
        double monthlyIncome = scanner.nextDouble();

        System.out.print("Чи є негативна кредитна історія? (true/false): ");
        boolean hasBadCreditHistory = scanner.nextBoolean();

        System.out.print("Введіть запитувану суму кредиту (грн): ");
        double loanAmount = scanner.nextDouble();

        System.out.print("Введіть бажаний термін (місяців): ");
        int loanMonths = scanner.nextInt();

        if (monthlyIncome <= 0 || loanAmount <= 0) {
            System.out.println("\nПОМИЛКА: Дохід та сума кредиту повинні бути більшими за 0.");
            scanner.close();
            return;
        }

        boolean isAgeValid = (age >= 21 && age <= 65);

        boolean isCreditHistoryGood = !hasBadCreditHistory;

        boolean isPaymentAffordable = (loanMonths > 0)
                && ((loanAmount / loanMonths) <= (monthlyIncome * 0.5));

        double monthlyPayment = loanMonths > 0
                ? loanAmount / loanMonths : 0.0;

        double maxPayment = monthlyIncome * 0.5;

        double paymentPercentage = loanMonths > 0
                ? (monthlyPayment / monthlyIncome) * 100 : 0.0;

        double interestRate = (monthlyIncome >= 35000)
                ? 14.5 : 21.0;

        boolean isLoanApproved = isAgeValid
                && isCreditHistoryGood
                && isPaymentAffordable;

        System.out.println("\n==============================================");
        System.out.println("          РІШЕННЯ КРЕДИТНОГО СКОРИНГУ");
        System.out.println("==============================================");

        if (isLoanApproved) {

            System.out.println("Статус заявки:              СХВАЛЕНО!");

            System.out.printf("Запитувана сума:             %.2f грн%n", loanAmount);

            System.out.printf("Термін кредиту:              %d місяців%n", loanMonths);

            System.out.printf("Орієнтовний платіж/міс:      %.2f грн%n",
                    monthlyPayment);

            System.out.printf("Навантаження на дохід:       %.1f%%%n",
                    paymentPercentage);

            System.out.printf("Персональна ставка:          %.1f%% річних%n",
                    interestRate);

            System.out.printf("Максимально допустимий платіж: %.2f грн/міс%n",
                    maxPayment);

            System.out.println("----------------------------------------------");
            System.out.println("Вітаємо! Вашу заявку на кредит схвалено.");
            System.out.println("Банк готовий надати вам кредит.");

        } else {

            System.out.println("Статус заявки:              ВІДХИЛЕНО");

            if (!isAgeValid) {

                System.out.println(
                        "Відмова: вік не відповідає критеріям (21-65 років)");

            } else if (!isCreditHistoryGood) {

                System.out.println(
                        "Відмова: виявлено негативну кредитну історію");

            } else if (!isPaymentAffordable) {

                System.out.println(
                        "Відмова: термін кредиту некоректний або щомісячний платіж перевищує 50% доходу");

            }

            System.out.println("----------------------------------------------");
            System.out.
                    println("На жаль, банк не може схвалити вашу заявку.");

        }

        System.out.println("==============================================");
        System.out.println("       ДЯКУЄМО ЗА ЗВЕРНЕННЯ ДО БАНКУ!");
        System.out.println("==============================================");

        scanner.close();
    }
}
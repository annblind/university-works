package semester1.practice05.homework;
import java.util.Scanner;
public class Task5RobloxPetSimulator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] petNames = {
                "Dog",
                "Cat",
                "Dragon",
                "Unicorn",
                "Huge Titanic Cat"
        };

        int[] petPowers = {15, 25, 120, 350, 2000};

        int[] dropChances = {45, 30, 15, 8, 2};

        String[] inventoryNames = new String[8];
        int[] inventoryPowers = new int[8];

        int petCount = 0;
        int coins = 300;

        int choice;

        System.out.println("======================================================");
        System.out.println("           ROBLOX: PET SIMULATOR");
        System.out.println("======================================================");

        do {

            System.out.println("\nБаланс: " + coins +
                    " монет | Вихованців у рюкзаку: " +
                    petCount + "/8");

            System.out.println("\n1 - Відкрити чарівне яйце (100 монет)");
            System.out.println("2 - Відкрити рюкзак-інвентар");
            System.out.println("3 - Порахувати силу команди");
            System.out.println("4 - Фармити монети (+50..150 монет)");
            System.out.println("0 - Вийти з гри");

            System.out.print("\nВаш вибір: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    if (coins < 100) {
                        System.out.println("Недостатньо монет! Потрібно 100 монет.");
                        break;
                    }

                    if (petCount >= inventoryNames.length) {
                        System.out.println("Рюкзак переповнений!");
                        System.out.println("Продайте або видаліть зайвих петів.");
                        break;
                    }

                    coins -= 100;

                    System.out.println("\nЯйце розколюється...");

                    int roll = (int) (Math.random() * 100) + 1;

                    int selectedPet = 0;
                    int chanceSum = 0;

                    for (int i = 0; i < dropChances.length; i++) {

                        chanceSum += dropChances[i];

                        if (roll <= chanceSum) {
                            selectedPet = i;
                            break;
                        }
                    }

                    inventoryNames[petCount] = petNames[selectedPet];
                    inventoryPowers[petCount] = petPowers[selectedPet];

                    petCount++;

                    if (selectedPet == 4) {
                        System.out.println("\n⭐⭐⭐ НЕЙМОВІРНО! СУПЕР-ДЖЕКПОТ! ⭐⭐⭐");
                    } else {
                        System.out.println("\nВам випав вихованець!");
                    }

                    System.out.println("Вихованець: " +
                            petNames[selectedPet]);

                    System.out.println("Сила: " +
                            petPowers[selectedPet]);

                    System.out.println("Вихованця додано в інвентар!");

                    System.out.println("Залишок монет: " + coins);

                    break;

                case 2:

                    System.out.println("\n========== ВАШ РЮКЗАК ==========");

                    if (petCount == 0) {
                        System.out.println("Ваш рюкзак поки порожній!");
                    } else {

                        for (int i = 0; i < petCount; i++) {

                            System.out.println("[Слот " + (i + 1) + "] "
                                    + inventoryNames[i]
                                    + " (Сила: "
                                    + inventoryPowers[i] + ")");
                        }
                    }

                    System.out.println("Зайнято слотів: " +
                            petCount + "/8");

                    System.out.println("================================");

                    break;

                case 3:
                    System.out.println("\n--- СТАТИСТИКА ВАШОЇ КОМАНДИ ---");

                    if (petCount == 0) {
                        System.out.println("У вас ще немає вихованців!");
                        break;
                    }

                    int totalPower = 0;
                    int maxPower = inventoryPowers[0];
                    int bestPetIndex = 0;

                    for (int i = 0; i < petCount; i++) {

                        totalPower += inventoryPowers[i];

                        if (inventoryPowers[i] > maxPower) {
                            maxPower = inventoryPowers[i];
                            bestPetIndex = i;
                        }
                    }

                    System.out.println("Кількість вихованців: "
                            + petCount + " шт.");

                    System.out.println("Загальна сила команди: "
                            + totalPower + " очок шкоди");

                    System.out.println("Найсильніший улюбленець: "
                            + inventoryNames[bestPetIndex]);

                    System.out.println("Сила найкращого пета: "
                            + maxPower);

                    System.out.println("--------------------------------");

                    break;

                case 4:

                    int earnedCoins = (int) (Math.random() * 101) + 50;

                    coins += earnedCoins;

                    System.out.println("\nВи фармите монети!");

                    System.out.println("Ви заробили: "
                            + earnedCoins + " монет!");

                    System.out.println("Ваш новий баланс: "
                            + coins + " монет.");

                    break;

                case 0:

                    System.out.println("\nДякуємо за гру!");
                    System.out.println("Ваш фінальний баланс: "
                            + coins + " монет.");

                    System.out.println("Кількість вихованців: "
                            + petCount + "/8");

                    System.out.println("До зустрічі в Roblox!");

                    break;

                default:

                    System.out.println("Неправильний вибір! Спробуйте ще раз.");

            }

        } while (choice != 0);

        scanner.close();
    }
}
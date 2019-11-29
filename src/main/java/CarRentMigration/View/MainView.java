package CarRentMigration.View;

import CarRentMigration.Controller.*;

public class MainView {
    public static void main(String[] args) {
        boolean exit = false;
        while(!exit) {
            System.out.println("Choose option: ");
            System.out.println("1. Migrate users \n2. Migrate admins \n3. Migrate drivers\n" +
                    "4. Migrate cars \n5. Migrate trucks \n6. Migrate parkings \n7. Exit");
            switch (UserInput.getUserChoice()) {
                case 1: {
                    UserController userController = new UserControllerImpl();
                    try {
                        userController.migrateUsersToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 2: {
                    AdminController adminController = new AdminControllerImpl();
                    try {
                        adminController.migrateAdminsToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 3: {
                    DriverController driverController = new DriverControllerImpl();
                    try {
                        driverController.migrateDriversToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 4: {
                    CarController carController = new CarControllerImpl();
                    try {
                        carController.migrateCarsToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 5: {
                    TruckController truckController = new TruckControllerImpl();
                    try {
                        truckController.migrateTrucksToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 6: {
                    ParkingController parkingController = new ParkingControllerImpl();
                    try {
                        parkingController.migrateParkingsToDb();
                    }
                    catch (ControllerException c) {
                        System.out.println(c.toString());
                    }
                    break;
                }
                case 7: {
                    exit = true;
                }
            }
        }
    }
}

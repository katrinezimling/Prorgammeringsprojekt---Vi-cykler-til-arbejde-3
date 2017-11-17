package controller;

import data.Data;
import model.Team;
import model.User;

import java.util.Scanner;

public class MainController {

    private Data db;
    private User currentUser;

    private Scanner input = new Scanner(System.in);


    public MainController() {
        this.db = new Data();
        this.input = new Scanner(System.in);

    }


    public void run() {

        String username;
        int password;

        while (currentUser == null)
            do {
                printStartMenu();
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        Login();
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        createTeam();
                        break;
                    default:
                        break;

                }
            } while (currentUser != null);
    }

    public void printStartMenu() {
        System.out.println("");
        System.out.println("START MENU  ");
        System.out.println("");
        System.out.println("1) Log Ind ");
        System.out.println("2) Opret Bruger ");
        System.out.println("3) Opret Hold");
    }

    public void Login() {
        input.nextLine();
        String username;
        int password;
        System.out.println("Indstast Brugernavn");
        username = input.nextLine();
        System.out.println("Indtast Password");
        password = input.nextInt();
        input.nextLine();

        currentUser = authenticateUser(username, password);

            if (currentUser.getType().equals("Administrator")) {
                AdminController adminController = new AdminController(db);
                adminController.printAdministratorMenu();
            } else if (currentUser.getType().equals("Deltager")) {
                ParticipantController participantController = new ParticipantController(db);
                participantController.myFirstMehthod();
            } else if (currentUser.getType().equals("Holdkaptajn")) {
                CaptainController captainController = new CaptainController(db);
                captainController.printCaptionMenu();

            }
        }


    public User authenticateUser(String username, int password) {
        for (User user : db.getUsers()) {

            if (username.equals(user.getUserName()) && (password == user.getPassWord())) {
                currentUser = user;
            }
        }

        return currentUser;
    }

    public boolean authenticateParticipantUser(String username, int password) {
        for (User user : db.getUsers()) {

            if (username.equals(user.getUserName()) && (password == user.getPassWord())) {
                currentUser = user;
                return true;
            }
        }

        return false;
    }

    public void createUser() {
        input.nextLine();
        System.out.println("Brugernavn: ");
        String username = input.nextLine();
        System.out.println("Kodeord: ");
        int password = input.nextInt();
        input.nextLine();
        System.out.println("Cykelisttype: ");
        String cykelisttype = input.nextLine();
        System.out.println("Alder: ");
        int alder = input.nextInt();
        System.out.println("Email: ");
        String email = input.nextLine();
        String id = input.nextLine();
        System.out.println("Navn: ");
        String navn = input.nextLine();

        //Opret User object
        User user = new User();

        user.setUserName(username);
        user.setPassWord(password);
        user.setCykelistType(cykelisttype);
        user.setAlder(alder);
        user.setEmail(email);
        user.setId(id);
        user.setNavn(navn);

        // Tilføj til liste i DATA klassen
        db.getUsers().add(user);

        printUsers();
    }

    private void printUsers() {
        System.out.printf("%-5s %-15s %-15s %-15s %-15s %-25s %-15s \n", "ID", "USERNAME", "PASSWORD", "CYKELISTTYPE", "ALDER", "EMAIL", "NAVN");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-15s %-15s %-15s %-15s %-25s %-15s \n", userIndex, user.getUserName(), user.getPassWord(), user.getCykelistType(), user.getAlder(), user.getEmail(), user.getNavn());
        }

        System.out.println("\n Brugeren er nu blevet oprettet. ");
    }

    public void createTeam() {
        input.nextLine();
        System.out.println("Firma: ");
        String firm = input.nextLine();
        System.out.println("Kaptajn:");
        String captain = input.nextLine();

        Team team = new Team();

        team.setFirm(firm);
        team.setCaptain(captain);

        // Tilføj til liste i DATA klassen
        db.getTeams().add(team);
        printTeams();
    }

    private void printTeams() {
        System.out.printf("%-5s %-15s %-15s \n", "ID", "FIRMA", "KAPTAJN");
        for (Team team : db.getTeams()) {
            int userIndex = db.getTeams().indexOf(team);
            System.out.printf("%-5s %-15s %-15s \n", userIndex, team.getFirm(),team.getCaptain());
        }

        System.out.println("\n Holdet er nu blevet oprettet. ");
    }

}

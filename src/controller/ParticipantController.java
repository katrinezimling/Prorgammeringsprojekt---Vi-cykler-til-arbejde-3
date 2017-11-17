package controller;

import data.Data;
import model.Team;
import model.User;

import java.util.Objects;
import java.util.Scanner;

import static sun.misc.PostVMInitHook.run;

public class ParticipantController {

    Scanner input = new Scanner(System.in);

    private Data db;
    private User currentUser;

    public ParticipantController(Data db) {
        this.db = db;
        this.currentUser = currentUser;

    }

    public void myFirstMehthod() {

        System.out.println("   DELTAGER BRUGERMENU  ");
        System.out.println("");
        System.out.println("1) Redigér deltageroplysniner");
        System.out.println("2) Vis aktivitetsstatus");
        System.out.println("3) Vis oplysninger");
        System.out.println("4) Vis en oversigt over virksomheds hold og dets holdkaptajner");
        System.out.println("5) Vis en statistik over fordelingen af hold og hvilke virksomheder de repræsenterer");
        System.out.println("");
        System.out.println("0) LOG UD");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                editParticipantInformation();
                break;
            case 2:
                showActivity();
                break;
            case 3:
                showInformation();
                break;
            case 4:
                showOverveiwForFirmsAndCaptains();
                break;
            case 5:
                showStatistics();
                break;
            case 0:
                currentUser = null;
                break;
            default:
                System.out.println("Indtast et tal mellem 0-5");
                myFirstMehthod();
                break;
        } while (currentUser !=null);
    }


    public void editParticipantInformation () {

        int indexOfParticipantToChange;
        String newUsername;
        int newPassWord;
        String newType;
        String newCykelistType;
        int newAlder;
        String newEmail;
        String newId;
        String newName;
        String newAktivitetsstatus;


        User userToChange;

        System.out.println("Vælg et af nedstående punkter:");
        System.out.println("1)  Redigér navn:");
        System.out.println("2)  Redigér alder:");
        System.out.println("3)  Redigér ID:");
        System.out.println("4)  Redigér email:");
        System.out.println("5)  Redigér cyklisttype:");
        System.out.println("6)  Redigér brugertype:");
        System.out.println("7)  Redigér username:");
        System.out.println("8)  Redigér password:");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Redigér navn:"); break;
            case 2:
                System.out.println("Redigér alder:"); break;
            case 3:
                System.out.println("Redigér ID:"); break;
            case 4:
                System.out.println("Redigér email:"); break;
            case 5:
                System.out.println("Redigér cyklisttype "); break;
            case 6:
                System.out.println("Redigér username"); break;
            case 7:
                System.out.println("Redigér password"); break;

            default: System.out.println("Ugyldigt"); break;

        }
        printUser();

        System.out.println("Vælg person - herefter kan du redigere");
        indexOfParticipantToChange = input.nextInt();

        userToChange = db.getUsers().get(indexOfParticipantToChange);

        if (choice == 1) {
            System.out.println("Indtast nyt navn");
            input.nextLine();
            newName = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setNavn(newName);
        }

        else if (choice == 2) {
            System.out.println("Redigér Alder - Indtast ny Alder: ");
            newAlder = input.nextInt();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setAlder(newAlder);
        }
        else if (choice == 3) {
            System.out.println("Indtast nyt ID");
            input.nextLine();
            newId = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setId(newId);
        }

        else if (choice == 4) {
            System.out.println("Indtast ny email");
            input.nextLine();
            newEmail = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setEmail(newEmail);
        }

        else if (choice == 5) {
            System.out.println("Indtast ny Cykelisttype");
            input.nextLine();
            newCykelistType = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setCykelistType(newCykelistType);
        }


        else if (choice == 6) {
            System.out.println("Indtast nyt username");
            input.nextLine();
            newUsername = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setUserName(newUsername);
        }


        else if (choice == 7) {
            System.out.println("Indtast password");
            newPassWord = input.nextInt();
            userToChange.setPassWord(newPassWord);
        }

        else {
            System.out.println("Ugyldigt");
        }

        printUser();

    }


    public void showActivity(){

        System.out.println("Se aktivitetsstatur for:");
        System.out.println("1) Deltagere");
        System.out.println("2) Hold");
        System.out.println("3) Virksomhed");



        int Choice = input.nextInt();
        switch (Choice) {
            case 1:
                showParticipantActivity();
                break;
            case 2:
                showTeamActivity();
                break;
            case 3:
                showCompanyActivity();
                break;
            default:
                break;
        }

    }

    public void showInformation() {

        System.out.println("Vis Informationer om:");
        System.out.println("");
        System.out.println("1) Deltagere");
        System.out.println("2) Hold og dets deltager");
        System.out.println("3) Vis alle hold og deres deltagere");

        int Choice = input.nextInt();
        switch (Choice){
            case 1:
                showParticipantInformation();
                break;
            case 2:
                showTeamAndParticipantsInformation();
                break;
            case 3:
                showAllTeamsAndTheirParticipants();
                break;
            default:
                System.out.println("Vælg et tal mellem 1-3");
                showInformation();
                break;
        }


    }

    public  void showOverveiwForFirmsAndCaptains () {
        System.out.printf("%-5s %-25s %-8s\n", "NR.", "FIRMA", "KAPTAJN");
        System.out.println("");
        for (Team team : db.getTeams()) {
            int teamIndex = db.getUsers().indexOf(team);
            System.out.printf("%-5s %-25s %-8s\n", teamIndex, team.getFirm(), team.getCaptain());
        }
        input.nextLine();

    }

    public  void showStatistics() {

    }

    public void logOff() {

        String userChoice;

        System.out.println("vil du logge ud (j/n)");

        userChoice = input.nextLine();

        while (userChoice != null)

            if (Objects.equals(userChoice, "j")) {
            System.out.println("Du er nu logget ud");
            MainController mainController = new MainController();
            mainController.run();
            }else if (Objects.equals(userChoice, "n")){
                System.out.println("Vend tilbage til brugermenuen");
                ParticipantController participantController = new ParticipantController(db);
                participantController.myFirstMehthod();
            }else {
                userChoice = null;
            }

    }

    void printUser() {

        System.out.printf("%-5s %-25s %-8s %-8s %-20s %-15s %-15s %-15s %-10s\n", "NR.", "NAVN", "ALDER", "ID", "EMAIL", "CYKLISTTYPE", "BRUGERTYPE", "USERNAME", "PASSWORD");
        System.out.println("");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-25s %-8s %-8s %-20s %-15s %-15s %-15s %-10s\n", userIndex, user.getNavn(), user.getAlder(), user.getId(), user.getEmail(), user.getCykelistType(), user.getType(), user.getUserName(), user.getPassWord());
        }

    }

    public void showParticipantActivity () {

        System.out.printf("%-5s %-25s %-8s\n", "NR.", "NAVN", "STATUS");
        System.out.println("");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-25s %-8s\n", userIndex, user.getNavn(), user.getAktivitetsStatus());
        }
        input.nextLine();

    }

    public void showTeamActivity () {
        System.out.printf("%-5s %-25s %-15s %-15s %-15s\n", "NR", "KAPTAJN", "DISTANCE", "FIRMA", "AKTIVITETSSTATUS");
        System.out.println("");
        for (Team team : db.getTeams()) {
            int teamIndex = db.getTeams().indexOf(team);
            System.out.printf("%-5s %-25s %-15s %-15s %-15s\n", teamIndex, team.getCaptain(), team.getDistance(), team.getFirm(), team.getAktivitetsStatus());
        }


    }

    public  void showCompanyActivity () {
        System.out.printf("%-5s %-10s %-15s %-10s\n", "NR", "FIRMA", "HOLDKAPTAJN", "DISTANCE");
        System.out.println("");
        for (Team team : db.getTeams()) {
            int teamIndex = db.getTeams().indexOf(team);
            System.out.printf("%-5s %-10s %-15s %-10s\n", teamIndex, team.getFirm(), team.getCaptain(), team.getDistance());
        }
        input.nextLine();
    }

    public  void showParticipantInformation () {

        System.out.printf("%-5s %-25s %-8s %-8s %-20s %-15s %-15s %-15s %-10s\n", "NR.", "NAVN", "ALDER", "ID", "EMAIL", "CYKLISTTYPE", "BRUGERTYPE", "USERNAME", "PASSWORD");
        System.out.println("");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-25s %-8s %-8s %-20s %-15s %-15s %-15s %-10s\n", userIndex, user.getNavn(), user.getAlder(), user.getId(), user.getEmail(), user.getCykelistType(), user.getType(), user.getUserName(), user.getPassWord());
        }
        input.nextLine();
    }


    public  void showTeamAndParticipantsInformation () {

    }

    public  void showAllTeamsAndTheirParticipants () {
        System.out.printf("%-5s %-25s \n", "NR.", "DELTAGER");
        System.out.println("");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-25s\n", userIndex, user.getNavn());
        }
        for (Team team : db.getTeams()) {
            int teamIndex = db.getTeams().indexOf(team);
            System.out.printf("%-15s\n", team.getFirm());
        }
        input.nextLine();

    }


}

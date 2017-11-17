package controller;

import data.Data;
import model.Team;
import model.User;

import java.util.Scanner;


public class AdminController extends CaptainController{

Scanner input = new Scanner(System.in);

private Data db;
private User currentUser;

public AdminController (Data db, User currentUser){
    super(db, currentUser);
    this.db = db;
    this.currentUser = currentUser;


}

public void printAdministratorMenu() {

    do {


        System.out.println("   ADMINISTRATOR BRUGERMENU:  ");
        System.out.println("");
        System.out.println("1) Redigér deltageroplysniner");
        System.out.println("Test");
        System.out.println("2) Redigér holdoplysniner");
        System.out.println("3) Vis aktivitetsstatus");
        System.out.println("4) Vis oplysninger");
        System.out.println("5) Vis en oversigt over virksomheds hold og dets holdkaptajner");
        System.out.println("6) Vis en statistik over fordelingen af hold og hvilke virksomheder de repræsenterer");
        System.out.println("7) slet deltager");
        System.out.println("8) slet hold ");
        System.out.println("9) Godkend Hold");
        System.out.println("");
        System.out.println("0) LOG UD");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                editParticipantInformation();
                break;
            case 2:
                editTeamInformation();
                break;
            case 3:
                showActivity();
                break;
            case 4:
                showInformation();
                break;
            case 5:
                showOverveiwForFirmsAndCaptains();
                break;
            case 6:
                showStatistics();
                break;
            case 7:
                deleteParticipant();
                break;
            case 8:
                deleteTeam();
                break;
            case 9:
                confirmTeam();
                break;
            case 0:
                currentUser = null;
                break;
            default:
                break;
        }

    } while (currentUser != null);

}
    private void confirmTeam() {


    }

    private void deleteTeam() {
        printTeams();
        int indexOfTeamsToDelete;
        Team teamToDelete;

        System.out.println("Vælg hold");
        indexOfTeamsToDelete = input.nextInt();
        teamToDelete = this.db.getTeams().remove(indexOfTeamsToDelete);

        System.out.println("Holdet er nu slettet");

        printTeams();

    }

    private void printTeams() {
        System.out.printf("%-5s %-20s %-15s %-15s\n", "ID", "HOLDKAPTAJN", "DISTANCE", "VIRKSOMHED");
        for (Team team : db.getTeams()) {
            int teamIndex = db.getTeams().indexOf(team);
            System.out.printf("%-5s %-20s %-15s %-15s\n", teamIndex, team.getCaptain(), team.getDistance(), team.getFirm() );
        }
    }

    private void deleteParticipant() {
        printUsers();
        int indexOfUsersToDelete;
        User userToDelete;

        System.out.println("Vælg deltager");
        indexOfUsersToDelete = input.nextInt();
        this.db.getUsers().remove(indexOfUsersToDelete);

        System.out.println("Deltageren er nu slettet");

        printUsers();

    }

    private void printUsers() {
        System.out.printf("%-5s %-20s %-15s %-15s %-15s %-15s\n", "USERNAME", "PASSWORD", "TYPE", "CYKELISTTYPE", "ALDER", "EMAIL", "ID", "NAVN", "AKTIVITETSSTATUS");
        for (User user : db.getUsers()) {
            int userIndex = db.getUsers().indexOf(user);
            System.out.printf("%-5s %-20s %-15s %-15s %-15s %-15s\n", userIndex, user.getUserName(), user.getPassWord(), user.getType(), user.getCykelistType(), user.getAlder(), user.getEmail(), user.getId(), user.getNavn(), user.getAktivitetsStatus());
        }
    }

    @Override
    public void editParticipantInformation() {
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
                System.out.println("Redigér brugetype"); break;
            case 7:
                System.out.println("Redigér username"); break;
            case 8:
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
            System.out.println("Indtast ny Brugertype");
            input.nextLine();
            newType = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setType(newType);
        }

        else if (choice == 7) {
            System.out.println("Indtast nyt username");
            input.nextLine();
            newUsername = input.nextLine();
            //System.out.println("Redigér1:" + userToChange.getnewCykelistType() + newCykelistType());
            userToChange.setUserName(newUsername);
        }


        else if (choice == 8) {
            System.out.println("Indtast password");
            newPassWord = input.nextInt();
            userToChange.setPassWord(newPassWord);
        }

        else {
            System.out.println("Ugyldigt");
        }

        printUser();


    }

    public void createUser() {

    }

}

package org.example.managers;

import org.example.model.Client;

import java.util.List;

public class ClientManger {

    public static void printClientList(List<Client> clients) {

        //todo
    }


    //public static Client loginClient(String email, String password) {
    public static Client loginClient(){
        // todo implement view: where? /views, loginView

        // todo business logic: check if client exists, loginValidator

        Client hardCodedClient = new Client();
        hardCodedClient.setId("11111");
        hardCodedClient.setName("Albert");
        hardCodedClient.setEmail("albertprofe@gmail.com");

        // todo: implement logout
        // todo: implement logoutValidator
        // todo: implement logoutView
        // todo: implement logoutController
        // todo: implement logoutDispatcher
        // todo: implement logoutRunner
        // todo: add client to DataStore

        return hardCodedClient;
    }
}

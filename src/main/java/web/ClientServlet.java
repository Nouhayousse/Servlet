package web;

import Dao.ClientDao;
import entities.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ClientServlet extends HttpServlet {
    private ClientDao clientDao;

    @Override
    public void init() throws ServletException {
        clientDao = new ClientDao();
        Client client = new Client(null,"Youssefi","Nouhaila","nouha@gmail.com","0667578409");
        clientDao.addClient(client);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();
        //String path = req.getRequestURI().substring(req.getContextPath().length()); // ex: "/" ou "/liste" ou "/add"
        //System.out.println("DEBUG - requestURI: " + req.getRequestURI() + " | contextPath: " + req.getContextPath() + " | path: " + path);


        if("/".equals(path) || "/liste".equals(path)){

        List<Client> clients = clientDao.getAllClients();
        req.setAttribute("clients", clients);
        req.getRequestDispatcher("/WEB-INF/views/liste.jsp").forward(req, resp);

    }else if (path.equals("/add")){
            req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
        }
        else if (path.equals("/edit")) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                long id = Long.parseLong(idStr);
                Client client = clientDao.getClient(id);
                req.setAttribute("client", client);
                req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
                return;
            }
        }
        else if (path.equals("/delete")) {
            long id = Long.parseLong(req.getParameter("id"));
            clientDao.removeClient(id); // méthode à créer dans ClientDao
            resp.sendRedirect(req.getContextPath() + "/liste");
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();
        if("/add".equals(path)){
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");

            Client client = new Client(null,firstname,lastname,email,phone);
            clientDao.addClient(client);
            resp.sendRedirect(req.getContextPath()+"/liste");
        }
        else if (path.equals("/edit")) {
            Long id = Long.parseLong(req.getParameter("id"));
            String nom = req.getParameter("firstname");
            String prenom = req.getParameter("lastname");
            String email = req.getParameter("email");
            String telephone = req.getParameter("phone");

            Client client = new Client(id, nom, prenom, email, telephone);
            clientDao.updateClient(client); // méthode à créer dans ClientDao

            resp.sendRedirect(req.getContextPath() + "/liste");
        }

    }

   /* private void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/liste":
                List<Client> clients = clientDao.getAllClients();
                req.setAttribute("clients", clients);
                req.getRequestDispatcher("liste.jsp").forward(req, resp);
                break;

            case "/add":
                if("GET".equals(req.getParameter("action"))) {
                req.getRequestDispatcher("add.jsp").forward(req, resp);

                }else {
                    String firstName = req.getParameter("firstName");
                    String lastName = req.getParameter("lastName");
                    String email = req.getParameter("email");
                    String phone = req.getParameter("phone");
                    Client c=new Client(null,firstName,lastName,email,phone);
                    clientDao.addClient(c);
                    resp.sendRedirect(req.getContextPath()+"/liste");
                }
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/liste");
        }}*/

}
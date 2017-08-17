package pe.edu.tecsup.dataloader.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.tecsup.dataloader.models.Contact;
import pe.edu.tecsup.dataloader.models.Lead;
import pe.edu.tecsup.dataloader.models.User;
import pe.edu.tecsup.dataloader.repositories.ApplicationRepository;

import java.util.List;

/**
 * Created by ebenites on 16/05/2017.
 */
@Service
public class ApplicationService {

    private static Logger log = Logger.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<User> list() {
        log.info("calling list: ");
        return applicationRepository.list();
    }

    public List<Lead>  getLeads() throws Exception {
        log.info("calling getLeads: ");
        return applicationRepository.getLeads();
    }

    public void registerLead(Lead lead) throws Exception {
        log.info("calling registerLead: ");
        applicationRepository.registerLead(lead);
    }

    public List<Contact> getContacts() throws Exception {
        log.info("calling getContacts: ");
        return applicationRepository.getContacts();
    }

    public void registerContact(Contact contact) throws Exception {
        log.info("calling registerContact: ");
        applicationRepository.registerContact(contact);
    }

}

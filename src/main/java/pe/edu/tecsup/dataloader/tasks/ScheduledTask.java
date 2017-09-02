package pe.edu.tecsup.dataloader.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import pe.edu.tecsup.dataloader.models.Contact;
import pe.edu.tecsup.dataloader.models.Lead;
import pe.edu.tecsup.dataloader.models.User;
import pe.edu.tecsup.dataloader.services.ApplicationService;
import pe.edu.tecsup.dataloader.utils.Mailer;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by ebenites on 16/05/2017.
 */
@Component
public class ScheduledTask {

    private static final Logger log = Logger.getLogger(ScheduledTask.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private Mailer mailer;

    /**
     * Paths configuration
     */
    @Value("${path.csv.data}")
    private String CSVDATAPATH;

    @Value("${path.csv.output}")
    private String CSVOUTPUTPATH;

//    @Scheduled(cron="0 0 6 * * *")
//    @Scheduled(fixedRate = 5000)
    public void processingUesrs() {
        log.info("processingUesrs ...");
        try {

            List<User> users = applicationService.list();

            for (User user : users) {
                log.info("User: " + user.getFullname());
            }

            // CSV Writer
            ICsvBeanWriter writer = new CsvBeanWriter(new FileWriter("D:\\users.csv"), CsvPreference.STANDARD_PREFERENCE);

            writer.writeHeader("ID", "FULLNAME", "EMAIL");

            for(User user : users) {
                writer.write(user, "id", "fullname", "email");
            }

            writer.close();

            // CSV Reader
            ICsvBeanReader reader = new CsvBeanReader(new FileReader("D:\\users.csv"), CsvPreference.STANDARD_PREFERENCE);

            final CellProcessor[] processors = new CellProcessor[] {
                    new UniqueHashCode(new ParseInt()), // id (must be unique)
                    new NotNull(), // fullname
                    new Optional(), // email
            };

            reader.getHeader(true);

            User user;
            while( (user = reader.read(User.class, new String[]{"id", "fullname", "email"}, processors)) != null ) {
                log.info(String.format("lineNo=%s, rowNo=%s, user=%s", reader.getLineNumber(), reader.getRowNumber(), user));
            }

            reader.close();

        }catch (Exception e){
            log.error(e, e);
        }
    }

    @Value("${test:}")
    private String TEST;

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void test(){
        log.info("Test init " + TEST + "...");

        if("mail".equalsIgnoreCase(TEST)){
            mailer.sendMail("Test de correo");
        }else if("lead-write".equalsIgnoreCase(TEST)){
            processingWriteLeads();
        }else if("lead-read".equalsIgnoreCase(TEST)){
            processingReaderLeads();
        }else if("contact-write".equalsIgnoreCase(TEST)){
            processingWriteContacts();
        }else if("contact-read".equalsIgnoreCase(TEST)){
            processingReaderContacts();
        }
    }

    @Scheduled(cron="0 * * * * *")
    public void keepalive(){
        log.info(new Date() + " - keep alive ScheduledTask ...");
    }

    @Scheduled(cron="0 0 * * * *")
    public void processingWriteLeads() {
        log.info("processingWriteLeads ...");
        try {

            List<Lead> leads = applicationService.getLeads();
            log.info("leads: " + leads.size());

            // CSV Writer
            ICsvBeanWriter writer = new CsvBeanWriter(new FileWriter(CSVDATAPATH+"lead.csv"), CsvPreference.STANDARD_PREFERENCE);

            writer.writeHeader("SISID", "LASTNAME", "FIRSTNAME", "COMPANY", "MOBILEPHONE", "PHONE", "EMAIL", "STATUS", "LEADSOURCE", "OWNERID", "CRM_APELLIDO_MATERNO__C", "CRM_CARGO__C", "CRM_FECHA_LDP__C", "CRM_LEY_DATOS_PERSONALES__C", "CRM_AREA__C", "CRM_NUMERO_DOCUMENTO__C", "CRM_GENERO_SEXO__C", "CRM_DISTRITO_PARDOT__C", "CRM_FECHA_NACIMIENTO__C", "CRM_TIPO_DOCUMENTO_PERSONA__C", "CRM_TIPO_DOCUMENTO_EMPRESA__C", "CRM_NU_DOC_PERSONA__C", "CRM_NUM_DOC_EMPRESA__C", "CRM_TIPO_PERSONA_DEL__C", "CRM_SEDE__C", "CRM_OFICINA__C", "CRM_COMO_CONOCIO_TECSUP__C", "CRM_AREAS_INTERES__C", "CRM_PRODUCTO_INTERES_LISTA__C", "CRM_PRODUCTO_INTERES_TEXTO__C", "EMPRESA", "COMENTARIO", "SEGMENTO");

            for(Lead lead : leads) {
                log.info(lead);
                writer.write(lead, "sisid", "lastname", "firstname", "company", "mobilephone", "phone", "email", "status", "leadsource", "ownerid", "apematerno", "cargo", "fechalpd", "leyproteccion", "area", "documento", "sexo", "distrito", "nacimiento", "tipodocpersona", "tipodocempresa", "numdocpersona", "numdocempresa", "tipo", "sede", "oficina", "conocido", "areainteres", "familia", "producto", "empresa", "comentario", "segmento");
            }

            writer.close();

        }catch (Exception e){
            log.error(e, e);
            mailer.sendMail("ERROR en processingWriteLeads: " + e.toString());
        }
    }

    @Scheduled(cron="0 30 * * * *")
    public void processingReaderLeads() {
        log.info("processingReaderLeads ...");
        try {
            log.info("Reading Success ...");

            ICsvBeanReader reader = new CsvBeanReader(new FileReader(CSVOUTPUTPATH+"lead_success.csv"), CsvPreference.STANDARD_PREFERENCE);

            final CellProcessor[] processors = new CellProcessor[] {
                    new UniqueHashCode(new ParseInt()), // id (must be unique)
                    new NotNull(), // sisid
            };

            reader.getHeader(true);

            Lead lead;
            while( (lead = reader.read(Lead.class, "id", "sisid", "lastname", "firstname", "company", "mobilephone", "phone", "email", "status", "leadsource", "ownerid", "apematerno", "cargo", "fechalpd", "leyproteccion", "area", "documento", "sexo", "distrito", "nacimiento", "tipodocpersona", "tipodocempresa", "numdocpersona", "numdocempresa", "tipo", "sede", "oficina", "conocido", "areainteres", "familia", "producto", "empresa", "comentario", "segmento", "status")) != null ) {
                log.info(String.format("lineNo=%s, rowNo=%s, user=%s", reader.getLineNumber(), reader.getRowNumber(), lead));
                applicationService.registerLead(lead);
            }

            reader.close();


            log.info("Reading Errors ...");

            reader = new CsvBeanReader(new FileReader(CSVOUTPUTPATH+"lead_error.csv"), CsvPreference.STANDARD_PREFERENCE);

            reader.getHeader(true);

            StringBuilder message = new StringBuilder();
            while( (lead = reader.read(Lead.class, "sisid", "lastname", "firstname", "company", "mobilephone", "phone", "email", "status", "leadsource", "ownerid", "apematerno", "cargo", "fechalpd", "leyproteccion", "area", "documento", "sexo", "distrito", "nacimiento", "tipodocpersona", "tipodocempresa", "numdocpersona", "numdocempresa", "tipo", "sede", "oficina", "conocido", "areainteres", "familia", "producto", "empresa", "comentario", "segmento", "id")) != null ) {
                log.info(String.format("lineNo=%s, rowNo=%s, user=%s", reader.getLineNumber(), reader.getRowNumber(), lead));
                message.append(lead.getSisid()).append(": ").append(lead.getId()).append("\n");
            }

            // Send mail
            mailer.sendMail(message.toString());

        }catch (Exception e){
            log.error(e, e);
            mailer.sendMail("ERROR en processingReaderLeads: " + e.toString());
        }
    }


    @Scheduled(cron="0 0 2 * * *")
    public void processingWriteContacts() {
        log.info("processingWriteContacts ...");
        try {

            List<Contact> contacts = applicationService.getContacts();
            log.info("contacts: " + contacts.size());

            // CSV Writer
            ICsvBeanWriter writer = new CsvBeanWriter(new FileWriter(CSVDATAPATH+"contact.csv"), CsvPreference.STANDARD_PREFERENCE);

            writer.writeHeader("CRM_CODIGOALUMNO__C", "LEADSOURCE", "CRM_APELLIDO_MATERNO__C", "PRODUCTO_INTERES_TEXTO__C", "CRM_LEY_DATOS_PERSONALES__C", "EMAIL", "HOMEPHONE", "MOBILEPHONE", "CRM_DOMICILIO1__C", "CRM_SEGUNDO_NOMBRE__C", "RECORDTYPEID", "TIPO_DE_PERSONA__C", "CRM_SEDE__C", "CRM_FECHA_DATOS_PERSONALES__C", "DESCRIPTION", "FIRSTNAME", "CRM_OFICINA__C", "OWNERID", "CRM_ESTADO_CIVIL__C", "CRM_FECHA_NACIMIENTO__C", "CRM_NUMERO_DOCUMENTO__C", "CRM_TIPO_DOCUMENTO__C", "LASTNAME", "HED__GENDER__C");

            for(Contact contact : contacts) {
                log.info(contact);
                writer.write(contact, "crmcodigoalumnoc", "leadsource", "crmapellidomaternoc", "productointerestextoc", "crmleydatospersonalesc", "email", "homephone", "mobilephone", "crmdomicilio1c", "crmsegundonombrec", "recordtypeid", "tipodepersonac", "crmsedec", "crmfechadatospersonalesc", "description", "firstname", "crmoficinac", "ownerid", "crmestadocivilc", "crmfechanacimientoc", "crmnumerodocumentoc", "crmtipodocumentoc", "lastname", "hedgenderc");
            }

            writer.close();

        }catch (Exception e){
            log.error(e, e);
            mailer.sendMail("ERROR en processingWriteContacts: " + e.toString());
        }
    }

    @Scheduled(cron="0 30 2 * * *")
    public void processingReaderContacts() {
        log.info("processingReaderContacts ...");
        try {
            log.info("Reading Success ...");

            ICsvBeanReader reader = new CsvBeanReader(new FileReader(CSVOUTPUTPATH+"contact_success.csv"), CsvPreference.STANDARD_PREFERENCE);

            final CellProcessor[] processors = new CellProcessor[] {
                    new UniqueHashCode(new ParseInt()), // id (must be unique)
                    new NotNull(), // sisid
            };

            reader.getHeader(true);

            Contact contact;
            while( (contact = reader.read(Contact.class, "id", "crmcodigoalumnoc", "leadsource", "crmapellidomaternoc", "productointerestextoc", "crmleydatospersonalesc", "email", "homephone", "mobilephone", "crmdomicilio1c", "crmsegundonombrec", "recordtypeid", "tipodepersonac", "crmsedec", "crmfechadatospersonalesc", "description", "firstname", "crmoficinac", "ownerid", "crmestadocivilc", "crmfechanacimientoc", "crmnumerodocumentoc", "crmtipodocumentoc", "lastname", "hedgenderc", "status")) != null ) {
                log.info(String.format("lineNo=%s, rowNo=%s, user=%s", reader.getLineNumber(), reader.getRowNumber(), contact));
                applicationService.registerContact(contact);
            }

            reader.close();


            log.info("Reading Errors ...");

            reader = new CsvBeanReader(new FileReader(CSVOUTPUTPATH+"contact_error.csv"), CsvPreference.STANDARD_PREFERENCE);

            reader.getHeader(true);

            StringBuilder message = new StringBuilder();
            while( (contact = reader.read(Contact.class, "crmcodigoalumnoc", "leadsource", "crmapellidomaternoc", "productointerestextoc", "crmleydatospersonalesc", "email", "homephone", "mobilephone", "crmdomicilio1c", "crmsegundonombrec", "recordtypeid", "tipodepersonac", "crmsedec", "crmfechadatospersonalesc", "description", "firstname", "crmoficinac", "ownerid", "crmestadocivilc", "crmfechanacimientoc", "crmnumerodocumentoc", "crmtipodocumentoc", "lastname", "hedgenderc", "id")) != null ) {
                log.info(String.format("lineNo=%s, rowNo=%s, user=%s", reader.getLineNumber(), reader.getRowNumber(), contact));
                message.append(contact.getCrmcodigoalumnoc()).append(": ").append(contact.getId()).append("\n");
            }

            // Send mail
            mailer.sendMail(message.toString());

        }catch (Exception e){
            log.error(e, e);
            mailer.sendMail("ERROR en processingReaderContacts: " + e.toString());
        }
    }

}

package pe.edu.tecsup.dataloader.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.dataloader.models.Lead;
import pe.edu.tecsup.dataloader.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ebenites on 16/05/2017.
 */
@Repository
public class ApplicationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger log = Logger.getLogger(ApplicationRepository.class);

    public List<User> list(){
        log.info("calling list: ");

        String sql = "select codpersona, general.nombrecliente(codpersona) as nombres, email from general.gen_persona where esempleado='S' and email is not null  and rownum < 20 order by 2";

        List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException{
                User user = new User();
                user.setId(rs.getInt("codpersona"));
                user.setFullname(rs.getString("nombres"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        });

        log.info(list);

        return list;
    }

    public List<Lead>  getLeads() throws Exception {
        log.info("getLeads");
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

            simpleJdbcCall.withSchemaName("WEB").withCatalogName("PKG_WEB_CRM").withProcedureName("CANDIDATOS");

            SqlParameterSource in = new MapSqlParameterSource();

            Map<String, Object> out = simpleJdbcCall.execute(in);
            log.info(out);

            List<Map<String, Object>> recordset = (ArrayList<Map<String, Object>>) out.get("S_C_RECORDSET");
            log.info("Length of retrieved batches from database = "+recordset);

            List<Lead> leads = new ArrayList<>();

            for(Map<String, Object> record : recordset) {

                Lead lead = new Lead();
                lead.setSisid(record.get("ID")!=null?(String)record.get("ID"):null);
                lead.setLastname(record.get("LASTNAME")!=null?(String)record.get("LASTNAME"):null);
                lead.setFirstname(record.get("FIRSTNAME")!=null?(String)record.get("FIRSTNAME"):null);
                lead.setCompany(record.get("COMPANY")!=null?(String)record.get("COMPANY"):null);
                lead.setMobilephone(record.get("MOBILEPHONE")!=null?(String)record.get("MOBILEPHONE"):null);
                lead.setPhone(record.get("PHONE")!=null?(String)record.get("PHONE"):null);
                lead.setEmail(record.get("EMAIL")!=null?(String)record.get("EMAIL"):null);
                lead.setStatus(record.get("STATUS")!=null?(String)record.get("STATUS"):null);
                lead.setLeadsource(record.get("LEADSOURCE")!=null?(String)record.get("LEADSOURCE"):null);
                lead.setOwnerid(record.get("OWNERID")!=null?(String)record.get("OWNERID"):null);
                lead.setApematerno(record.get("CRM_APELLIDO_MATERNO__C")!=null?(String)record.get("CRM_APELLIDO_MATERNO__C"):null);
                lead.setCargo(record.get("CRM_CARGO__C")!=null?(String)record.get("CRM_CARGO__C"):null);
                lead.setFechalpd(record.get("CRM_FECHA_LDP__C")!=null?(String)record.get("CRM_FECHA_LDP__C"):null);
                lead.setLeyproteccion(record.get("CRM_LEY_DATOS_PERSONALES__C")!=null?(String)record.get("CRM_LEY_DATOS_PERSONALES__C"):null);
                lead.setArea(record.get("CRM_AREA__C")!=null?(String)record.get("CRM_AREA__C"):null);
                lead.setDocumento(record.get("CRM_NUMERO_DOCUMENTO__C")!=null?(String)record.get("CRM_NUMERO_DOCUMENTO__C"):null);
                lead.setSexo(record.get("CRM_GENERO_SEXO__C")!=null?(String)record.get("CRM_GENERO_SEXO__C"):null);
                lead.setDistrito(record.get("CRM_DISTRITO_PARDOT__C")!=null?(String)record.get("CRM_DISTRITO_PARDOT__C"):null);
                lead.setNacimiento(record.get("CRM_FECHA_NACIMIENTO__C")!=null?(String)record.get("CRM_FECHA_NACIMIENTO__C"):null);
                lead.setTipodocpersona(record.get("CRM_TIPO_DOCUMENTO_PERSONA__C")!=null?(String)record.get("CRM_TIPO_DOCUMENTO_PERSONA__C"):null);
                lead.setTipodocempresa(record.get("CRM_TIPO_DOCUMENTO_EMPRESA__C")!=null?(String)record.get("CRM_TIPO_DOCUMENTO_EMPRESA__C"):null);
                lead.setNumdocpersona(record.get("CRM_NU_DOC_PERSONA__C")!=null?(String)record.get("CRM_NU_DOC_PERSONA__C"):null);
                lead.setNumdocempresa(record.get("CRM_NUM_DOC_EMPRESA__C")!=null?(String)record.get("CRM_NUM_DOC_EMPRESA__C"):null);
                lead.setTipo(record.get("CRM_TIPO_PERSONA_DEL__C")!=null?(String)record.get("CRM_TIPO_PERSONA_DEL__C"):null);
                lead.setSede(record.get("CRM_SEDE__C")!=null?(String)record.get("CRM_SEDE__C"):null);
                lead.setOficina(record.get("CRM_OFICINA__C")!=null?(String)record.get("CRM_OFICINA__C"):null);
                lead.setConocido(record.get("CRM_COMO_CONOCIO_TECSUP__C")!=null?(String)record.get("CRM_COMO_CONOCIO_TECSUP__C"):null);
                lead.setArea(record.get("CRM_AREAS_INTERES__C")!=null?(String)record.get("CRM_AREAS_INTERES__C"):null);
                lead.setFamilia(record.get("CRM_PRODUCTO_INTERES_LISTA__C")!=null?(String)record.get("CRM_PRODUCTO_INTERES_LISTA__C"):null);
                lead.setProducto(record.get("CRM_PRODUCTO_INTERES_TEXTO__C")!=null?(String)record.get("CRM_PRODUCTO_INTERES_TEXTO__C"):null);

                leads.add(lead);
            }

            log.info("leads: " + leads);

            return leads;
        }catch (Exception e){
            log.error(e, e);
            throw e;
        }
    }

    public void registerLead(Lead lead) throws Exception {
        log.info("registerLead: lead:" + lead);
        try {
            String sql = "INSERT INTO WEB_INTERES_CRM (SISID, CRMID) VALUES (?, ?)";
            jdbcTemplate.update(sql, lead.getSisid(), lead.getId());
        }catch (Exception e){
            log.error(e, e);
//            throw e;
        }
    }

}

package com;

//import service.PatienService;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.Patient;
//For XML

@Path("/Patient")
public class PatientService {
	Patient patientmObj = new Patient();

	@GET
	@Path("/get-patients")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return patientmObj.readPatient();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("patientNIC") String patientNIC, @FormParam("patientfname") String patientfname, @FormParam("patientlname") String patientlname,
			@FormParam("patientgender") String patientgender, @FormParam("patientphone") String patientphone) {

		String output = patientmObj.insertPatient(patientNIC,patientfname, patientlname, patientgender, patientphone);
		return output;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(@FormParam("patientNIC") String patientNIC, @FormParam("patientfname") String patientfname, @FormParam("patientlname") String patientlname,
			@FormParam("patientgender") String patientgender, @FormParam("patientphone") String patientphone) {
		// Convert the input string to a JSON object
		// Read the values from the JSON object
		
		String output = patientmObj.updatePatient(patientNIC, patientfname, patientlname, patientgender, patientphone);
		return output;
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(@QueryParam("patientNIC") String patientNIC) {

		// Convert the input string to an XML document

		String output = patientmObj.deletePatient(patientNIC);

		return output;
	}
}

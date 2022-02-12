package com.service;

//For REST Service
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
//For JSON
import com.appointment.Appointment;
//For XML


@Path("/Appoinment")
public class AppointmentService {
	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/get-appoinments")
	@Produces(MediaType.TEXT_HTML)
	public String readAppoinment() {
		return appointmentObj.readAppoinment();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment
			(@FormParam("apt_ID") String apt_ID,
			@FormParam("discription") String discription,
			@FormParam("patient_id") String patient_id,
			@FormParam("doctor_id") String doctor_id,
			@FormParam("hospital_id") String hospital_id,
			@FormParam("charge") String charge,
			@FormParam("datetime") String datetime
			){

		String output = appointmentObj.insertAppoinment(apt_ID, discription, patient_id,doctor_id,hospital_id,datetime,charge);
		return output;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(
			@FormParam("apt_ID") String apt_ID,
			@FormParam("discription") String discription,
			@FormParam("patient_id") String patient_id,
			@FormParam("doctor_id") String doctor_id,
			@FormParam("hospital_id") String hospital_id,
			@FormParam("charge") String charge,
			@FormParam("datetime") String datetime
			) {
		// Convert the input string to a JSON object
//		JsonObject appObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		// Read the values from the JSON object
		

		String output = appointmentObj.updateappoinment(apt_ID, discription, patient_id,doctor_id,hospital_id,datetime,charge);
		return output;
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(@QueryParam("apt_ID") String apt_ID) 
	{
		
		// Convert the input string to an XML document
//		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());
		
		// Read the value from the element <appointmentID>
//		String apt_ID = doc.select("apt_ID").text();
			 
		String output = appointmentObj.deleteappoinment(apt_ID);
		
		return output;
	}
}


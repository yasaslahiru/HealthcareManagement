package com.service;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.doctor.*;


@Path("/Doctor")
public class DoctorService {
	Doctor docObj = new Doctor();

	@GET
	@Path("/get-doctors")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return docObj.readItems();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(
			@FormParam("DoctorID") String DoctorID,
			@FormParam("DoctorName") String DoctorName, 
			@FormParam("DoctorGender") String DoctorGender,
			@FormParam("DoctorSpecialist") String DoctorSpecialist) {

		String output = docObj.insertDoctor(DoctorID, DoctorName, DoctorGender, DoctorSpecialist);
		return output;
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(@FormParam("DoctorID") String DoctorID,
			@FormParam("DoctorName") String DoctorName, 
			@FormParam("DoctorGender") String DoctorGender,
			@FormParam("DoctorSpecialist") String DoctorSpecialist) {

		String output = docObj.updateDoctor(DoctorID, DoctorName, DoctorGender, DoctorSpecialist);
		return output;
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(@QueryParam("DoctorID")String DoctorID) 
	{
			 
		String output = docObj.deleteDoctor(DoctorID);
		
		return output;
	}
}
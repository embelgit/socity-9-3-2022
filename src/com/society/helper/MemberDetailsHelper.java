package com.society.helper;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.society.bean.ExpenditurePaymentDetail;
import com.society.bean.GetMemberDetailsBean;
import com.society.bean.MaintenanceDetailsBean;
import com.society.bean.MemberDetailsBean;
import com.society.dao.ExpenditurePaymentDao;
import com.society.dao.MaintenanceDetailsDao;
import com.society.dao.MemberDetailsDao;
import com.society.dao.MemberMonthlyContributionCostDao;
import com.society.hibernate.EmployeeLeaveHibernate;
import com.society.hibernate.MemberDetailsHibernate;
import com.society.hibernate.MemberMonthlyContributionCostHibernate;
import com.society.hibernate.MonthlyContributionCostHibernate;
import com.society.utility.HibernateUtility;

public class MemberDetailsHelper 
{
	public void doMemDetails(HttpServletRequest request,HttpServletResponse response) 
	{
		// TODO Auto-generated method stub
		System.out.println("In helper");
		

		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String contactNo = request.getParameter("contactNo");
		String altContactNo = request.getParameter("altContactNo");
		String emailId = request.getParameter("emailId");	
		String confirmEmail = request.getParameter("confirmEmail");
		String buildName = request.getParameter("buildingName");
		String wgName = request.getParameter("wingName");
		String flrNo = request.getParameter("floorNo");
		String fltNo = request.getParameter("flatNo");
		String position = request.getParameter("position");
		String adharNo = request.getParameter("adharNo");
		String panNo = request.getParameter("panNo");
		String sba = request.getParameter("sba");
		
		// to upper case
		String buildingName = buildName.toUpperCase();
		String wingName = wgName.toUpperCase();
		String floorNo = flrNo.toUpperCase();
		String flatNo = fltNo.toUpperCase();
		
	/*	
		String idNumber = request.getParameter("idNumber");
		String designation = request.getParameter("designation");
		String adharNumber = request.getParameter("adharNumber");
		String panNumber = request.getParameter("panNumber");				
		String education = request.getParameter("education");
		String technology = request.getParameter("technology");
		String previousExperience = request.getParameter("previousExperience");
		String salary = request.getParameter("salary");
		String referenceBy = request.getParameter("referenceBy");
		String interviewedBy = request.getParameter("interviewedBy");
		String dateOfJoining = request.getParameter("dateOfJoining");
		String department = request.getParameter("department");
		String prevCompanyName = request.getParameter("prevCompanyName");
		String zipCode = request.getParameter("zipCode");
		String comment = request.getParameter("comment");
		String employeeCondition = request.getParameter("employeecondition");
		String placeofposting = request.getParameter("placeofposting");
		String university = request.getParameter("university");
		
		String description1 = request.getParameter("description1");
		String description2 = request.getParameter("description2");
		
		String currentAddress = request.getParameter("currentAddress");
		String permanentAddress = request.getParameter("permanentAddress");
	*/	
		//String empFullName = firstName+" "+lastName;
		
		MemberDetailsHibernate edh = new MemberDetailsHibernate();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateOfBirth = null;
		try{
			dateOfBirth = format.parse(dob);
			edh.setDob(dateOfBirth);
			System.out.println(" date dateOfBirth parsing" +dob);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in date parsing");
		}
		
		/*Date joiningDate = null;
		try{
			joiningDate = format.parse(dateOfJoining);
			edh.setDateOfJoining(joiningDate);
			System.out.println(" date dateOfBirth parsing" +dateOfJoining);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in date parsing");
		}*/
		
/*		Date leavingDate = null;
		try{
			leavingDate = format.parse(dateOfLiving);
			edh.setDateOfLiving(leavingDate);
			System.out.println(" date dateOfBirth parsing" +dateOfLiving);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in date parsing");
		}*/
		
		// validate fields 
		
				edh.setPosition(position);
				edh.setFirstName(firstName);
				edh.setLastName(lastName);
				edh.setEmailId(emailId);
				edh.setContactNo(Long.parseLong(contactNo));
				
				edh.setBuildingName(buildingName);
				edh.setWingName(wingName);
				edh.setFloorNo(floorNo);
				edh.setFlatNo(flatNo);
				edh.setSba(Long.parseLong(sba));
				
				
				if(!"".equals(adharNo)){
					edh.setAdharNo(Long.parseLong(adharNo));
					
				}else{
					edh.setAdharNo(Long.parseLong("00"));
				}
				
				
				if(!"".equals(panNo)){
					edh.setPanNo(panNo);
					
				}else{
					edh.setPanNo("N/A");
				}
				
				if(!"".equals(middleName)){
					edh.setMiddleName(middleName);
					
				}else{
					edh.setMiddleName("N/A");
				}
			
				
				if(!"".equals(altContactNo)){
					edh.setAltContactNo(altContactNo);
					
				}else{
					edh.setAltContactNo("N/A");
					
				}
				
				if(!"".equals(confirmEmail)){
					edh.setConfirmEmail(confirmEmail);
					
				}else{
					edh.setConfirmEmail("N/A");
				}
				
			/*	edh.setDesignation(designation);
				edh.setEducation(education);
				edh.setSalary(Double.parseDouble(salary));
				edh.setZipCode(Long.parseLong(zipCode));
				

				edh.setStatus("Active");
				
				if(!"".equals(adharNumber)){
					edh.setAdharNumber(Long.parseLong(adharNumber));
					
				}else{
					edh.setAdharNumber(Long.parseLong("00"));
				}
				
				
				
				if(!"".equals(panNumber)){
					edh.setPanNumber(panNumber);
					
				}else{
					edh.setPanNumber("N/A");
				}
				
				

				if(!"".equals(university)){
					edh.setUniversity(university);
					
				}else{
					edh.setUniversity("N/A");
				}
				
				
				if(!"".equals(placeofposting)){
					edh.setPlaceOfPosting(placeofposting);
					
				}else{
					edh.setPlaceOfPosting("N/A");
				}
				
				
				if(!"".equals(technology)){
					edh.setTechnology(technology);
					
				}else{
					edh.setTechnology("N/A");
				}
			*/	
				
				
				
			/*	if(!"".equals(idNumber)){
                    edh.setIdNumber(idNumber);
                    
				}else{
                    edh.setIdNumber("N/A");
				}
				
			
				
				if(!"".equals(previousExperience)){
					edh.setPreviousExperience(previousExperience);
					
				}else{
					edh.setPreviousExperience("N/A");
				}
				
				
				if(!"".equals(referenceBy)){
					edh.setReferenceBy(referenceBy);
					
				}else{
					edh.setReferenceBy("N/A");
				}
				
				
				
				if(!"".equals(interviewedBy)){
					edh.setInterviewedBy(interviewedBy);
					
				}else{
					edh.setInterviewedBy("N/A");
					
				}
				
				
				
				if(!"".equals(prevCompanyName)){
					edh.setPrevCompanyName(prevCompanyName);
					

				}else{
					edh.setPrevCompanyName("N/A");
					
				}
*/
				
				

				
			/*	if(!"".equals(comment)){
					edh.setComment(comment);
					
				}else{
					edh.setComment("N/A");
				}

				
				if(!"".equals(employeeCondition)){
					edh.setEmployeeCondition(employeeCondition);
				
				}else{
					edh.setEmployeeCondition("N/A");
				}

				
				if(!"".equals(department)){
					edh.setDepartment(department);
					
				}else{
					edh.setDepartment("N/A");
				}

				
				
				if(!"".equals(currentAddress)){
					edh.setCurrentAddress(currentAddress);
					
				}else{
					edh.setCurrentAddress("N/A");
				}
				
				
				if(!"".equals(permanentAddress)){
					edh.setPermanentAddress(permanentAddress);
					
				}else{
					edh.setPermanentAddress("N/A");
				}
				*/
				
				
		/*		
				
				edh.setIdNumber(idNumber);
				edh.setAltContactNo(Long.parseLong(altContactNo));
				edh.setPreviousExperience(previousExperience);
				edh.setReferenceBy(referenceBy);
				edh.setInterviewedBy(interviewedBy);
				edh.setPrevCompanyName(prevCompanyName);
				edh.setConfirmEmail(confirmEmail);
				edh.setComment(comment);
				edh.setEmployeeCondition(employeeCondition);
				edh.setDepartment(department);
	*/			
				
				MemberDetailsDao edo = new MemberDetailsDao();
				edo.valMemberDetails(edh);
				
	
/*				MemberDetailsDao mdDao = new MemberDetailsDao();
				List list22 = mdDao.getAllMemberList();*/
				
				
				Date d = new Date();
				
				String currentDate = d.toString();
				String [] d2 = currentDate.split(" ");
				
				String month = d2[1];
				Double monthlyContributionCost = null;
				Long pkMemberId = (long) 0;
				
				
		/*	// get pk Id from member details
				MemberDetailsDao mdDao = new MemberDetailsDao();
				List<MemberDetailsHibernate> memIdList = mdDao.getPkIdOfMember();
				for(int k=0;k<memIdList.size();k++)
				{
					MemberDetailsHibernate mdh = (MemberDetailsHibernate)memIdList.get(k);
					pkMemberId = mdh.getPkMemId();
				}
				
				
			
			// get monthly Contribution Cost from monthly contribution cost details
				MemberMonthlyContributionCostDao mmcDao = new MemberMonthlyContributionCostDao();
				List<MonthlyContributionCostHibernate> CostList = mmcDao.getMonthlyContributionCost(month);
				for(int j=0;j<CostList.size();j++)
				{
					MonthlyContributionCostHibernate mcch = (MonthlyContributionCostHibernate)CostList.get(j);
					monthlyContributionCost = mcch.getMonthlyContributionCost();
				}
			
				MemberMonthlyContributionCostHibernate mmcch = new MemberMonthlyContributionCostHibernate();
							
				mmcch.setFkMemId(pkMemberId);
				mmcch.setFirstName(firstName);
				
				if(!"".equals(middleName)){
					mmcch.setMiddleName(middleName);
				}else{
					mmcch.setMiddleName("N/A");
				}
				
				mmcch.setContactNo(Long.parseLong(contactNo));
				mmcch.setLastName(lastName);
				mmcch.setMonthlyContributionCost(monthlyContributionCost);
				mmcch.setMonth(month);
				mmcch.setBalanceAmount(monthlyContributionCost);
				
				
				MemberMonthlyContributionCostDao mmccDao = new MemberMonthlyContributionCostDao();
				mmccDao.addMemberMonthlyContributionCostDetails(mmcch);
				
*/				
				
	}
	
	
	
	public List getMemDetail(HttpServletRequest request, HttpServletResponse response) 
	{
		String fkMemberid = request.getParameter("fkMemberid");
		String memberName = request.getParameter("memberName");
		
		Map<Long, GetMemberDetailsBean> map = new HashMap<Long, GetMemberDetailsBean>();

		MemberDetailsDao dao = new MemberDetailsDao();
		List<GetMemberDetailsBean> exp1List = dao.getMemberDetail(fkMemberid,memberName);

		return exp1List;

	}
	/*
	//employee Detailas for edit
	public List getEmployeeDetails(Long fkEmployeeid)
	{
		
		Map<Long, GetMemberDetailsBean> map = new HashMap<Long, GetMemberDetailsBean>();

		MemberDetailsDao dao = new MemberDetailsDao();
		List<GetMemberDetailsBean> exp1List = dao.getEmployeeDetailForEdit(fkEmployeeid);

		System.out.println("Size in Helper------ : "+exp1List.size());
		
		return exp1List;

	}
	*/
	
/*	public void updateEmployeeDetails(HttpServletRequest request,HttpServletResponse response)
	{
	
		String empId = request.getParameter("fkEmployeeid");

		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		String idNumber = request.getParameter("idNumber");
		String designation = request.getParameter("designation");
		String contactNo = request.getParameter("contactNo");
		String altContactNo = request.getParameter("altContactNo");
		String adharNumber = request.getParameter("adharNumber");
		String panNumber = request.getParameter("panNumber");				
		String emailId = request.getParameter("emailId");
		String address = request.getParameter("address");
		String education = request.getParameter("education");
		String technology = request.getParameter("technology");
		String previousExperience = request.getParameter("previousExperience");
		String salary = request.getParameter("salary");
		String referenceBy = request.getParameter("referenceBy");
		String interviewedBy = request.getParameter("interviewedBy");
		String dateOfJoining = request.getParameter("dateOfJoining");
		String department = request.getParameter("department");
		String prevCompanyName = request.getParameter("prevCompanyName");
		String zipCode = request.getParameter("zipCode");
		String confirmEmail = request.getParameter("confirmEmail");
		String comment = request.getParameter("comment");
		String employeeCondition = request.getParameter("employeecondition");
		String placeofposting = request.getParameter("placeofposting");
		
		System.out.println("From JSP ID  :   -----------   "+empId);
		
		MemberDetailsDao dao  =new MemberDetailsDao();
		List list22 = dao.getAllEmployee();
		
		 for(int i=0;i<=list22.size();i++)
		 {
			 MemberDetailsHibernate edh = (MemberDetailsHibernate) list22.get(i);
			 
			 Long empPkId = edh.getPkMemId();
			 String fName = edh.getFirstName();
			 String lName = edh.getLastName();		 
		
			 System.out.println("From DB ID  :   -----------   "+empPkId);
			 
			 if(fName.equals(firstName) && lName.equals(lastName) && empPkId.equals(Long.parseLong(empId)))
			 {

					HibernateUtility hbu = HibernateUtility.getInstance();
					Session session = hbu.getHibernateSession();
					Transaction transaction = session.beginTransaction();
					
					MemberDetailsHibernate purchase = (MemberDetailsHibernate) session.get(MemberDetailsHibernate.class,new Long(empPkId));
				 
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					
					Date dateOfBirth = null;
					try{
						dateOfBirth = format.parse(dob);
						purchase.setDob(dateOfBirth);
						System.out.println(" date dateOfBirth parsing" +dob);
					}
					catch(Exception e){
						e.printStackTrace();
						System.out.println("Exception in date parsing");
					}
					
					Date joiningDate = null;
					try{
						joiningDate = format.parse(dateOfJoining);
						purchase.setDateOfJoining(joiningDate);
						System.out.println(" date dateOfBirth parsing" +dateOfJoining);
					}
					catch(Exception e){
						e.printStackTrace();
						System.out.println("Exception in date parsing");
					}
					
		
							purchase.setFirstName(firstName);
							purchase.setLastName(lastName);
							purchase.setDesignation(designation);
							purchase.setContactNo(Long.parseLong(contactNo));
							purchase.setAdharNumber(Long.parseLong(adharNumber));
							purchase.setPanNumber(panNumber);
							purchase.setEmailId(emailId);
							purchase.setAddress(address);
							purchase.setEducation(education);
							purchase.setTechnology(technology);
							purchase.setSalary(Double.parseDouble(salary));
							purchase.setZipCode(Long.parseLong(zipCode));
							purchase.setPlaceOfPosting(placeofposting);

								
							if(!"".equals(middleName)){
								purchase.setMiddleName(middleName);
								
							}else{
								purchase.setMiddleName("N/A");
							}
							
							
							if(!"".equals(idNumber)){
								purchase.setIdNumber(idNumber);
			                    
							}else{
								purchase.setIdNumber("N/A");
							}
							
							
							if(!"".equals(altContactNo)){
								purchase.setAltContactNo(altContactNo);
								
							}else{
								purchase.setAltContactNo("N/A");
								
							}
							
							
							if(!"".equals(previousExperience)){
								purchase.setPreviousExperience(previousExperience);
								
							}else{
								purchase.setPreviousExperience("N/A");
							}
							
							
							if(!"".equals(referenceBy)){
								purchase.setReferenceBy(referenceBy);
								
							}else{
								purchase.setReferenceBy("N/A");
							}
							
							
							
							if(!"".equals(interviewedBy)){
								purchase.setInterviewedBy(interviewedBy);
								
							}else{
								purchase.setInterviewedBy("N/A");
								
							}
							
							
							
							if(!"".equals(prevCompanyName)){
								purchase.setPrevCompanyName(prevCompanyName);
								

							}else{
								purchase.setPrevCompanyName("N/A");
								
							}

							
							if(!"".equals(confirmEmail)){
								purchase.setConfirmEmail(confirmEmail);
								
							}else{
								purchase.setConfirmEmail("N/A");
							}

							
							if(!"".equals(comment)){
								purchase.setComment(comment);
								
							}else{
								purchase.setComment("N/A");
							}

							
							if(!"".equals(employeeCondition)){
								purchase.setEmployeeCondition(employeeCondition);
							
							}else{
								purchase.setEmployeeCondition("N/A");
							}

							
							if(!"".equals(department)){
								purchase.setDepartment(department);
								
							}else{
								purchase.setDepartment("N/A");
							}

							
							session.update(purchase);
							transaction.commit();
						
							break;

					
			 }
			 
		 }

	}
	*/
/*	
	// Previous Employee Details For Reports
	public List getExEmpDetail(HttpServletRequest request, HttpServletResponse response) {
		String employeeName = request.getParameter("employeeName");
		
		Map<Long, GetMemberDetailsBean> map = new HashMap<Long, GetMemberDetailsBean>();

		MemberDetailsDao dao = new MemberDetailsDao();
		List<GetMemberDetailsBean> exp1List = dao.getExEmployeeDetail(employeeName);

		return exp1List;

	}*/
	
	
	public void doEmpDetailLeaveDetails(HttpServletRequest request,HttpServletResponse response) 
	{
		
		String fkEmployeeid = request.getParameter("fkEmployeeid");
		String employeeName = request.getParameter("employeeName");
		String leaveDateFrom = request.getParameter("leaveDateFrom");
		String type = request.getParameter("type");
		String leaveDateTo = request.getParameter("leaveDateTo");
		String description = request.getParameter("description");
		String approvedBy = request.getParameter("approvedBy");
		
	/*	System.out.println("-----------------fkEmployeeid-------------------  :::  "+fkEmployeeid);
		System.out.println("----------------employeeName--------------------  :::  "+employeeName);
		System.out.println("------------------leaveDateFrom------------------  :::  "+leaveDateFrom);
		System.out.println("-----------------type-------------------  :::  "+type);
		System.out.println("-----------------leaveDateTo-------------------  :::  "+leaveDateTo);
		System.out.println("------------------description------------------  :::  "+description);
		System.out.println("-----------------approvedBy-------------------  :::  "+approvedBy);
	*/	
		EmployeeLeaveHibernate elh = new EmployeeLeaveHibernate();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date leaveFrom = null;
		try{
			leaveFrom = format.parse(leaveDateFrom);
			elh.setLeaveDateFrom(leaveFrom);
			System.out.println(" date leaveFrom parsing" +leaveFrom);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in date parsing");
		}
		
		Date leaveTo = null;
		try{
			leaveTo = format.parse(leaveDateTo);
			elh.setLeaveDateTo(leaveTo);
			System.out.println(" date leaveTo parsing" +leaveTo);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in date parsing");
		}
		
		elh.setEmpFkId(Long.parseLong(fkEmployeeid));;
		elh.setEmpName(employeeName);
		elh.setType(type);
		
	
		if(!"".equals(description)){
			elh.setDescription(description);
			
		}else{
			elh.setDescription("N/A");
		}
		
		if(!"".equals(approvedBy)){
			elh.setApprovedBy(approvedBy);
			
		}else{
			elh.setApprovedBy("N/A");
		}

		MemberDetailsDao edo = new MemberDetailsDao();
		edo.employeeLaveDetails(elh);
	}
	
	
	// get Member Details For Compliant Or Enquiry
	public List getMemberDetailsForComOrEnq(Long fkMemberid)
	{
		Map<Long, MemberDetailsBean> map = new HashMap<Long, MemberDetailsBean>();

		MemberDetailsDao dao = new MemberDetailsDao();
		List<MemberDetailsBean> cli1List = dao.getMemberDetailsForComOrEnq(fkMemberid);

		return cli1List;

	}
	
	//Member Details for edit
	public List getMemberDetailsForEdit(Long fkMemberId)
	{
		Map<Long, MemberDetailsBean> map = new HashMap<Long, MemberDetailsBean>();

		MemberDetailsDao dao = new MemberDetailsDao();
		List<MemberDetailsBean> ven1List = dao.getMemberDetailsForEdit(fkMemberId);

		return ven1List;
	}
	
	
	// Updsate Member Details
	public void updateMemberDetails(HttpServletRequest request,HttpServletResponse response) 
	{
		
			String fkMemberId = request.getParameter("fkMemberId");
			
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			String dob = request.getParameter("dob");
			String contactNo = request.getParameter("contactNo");
			String altContactNo = request.getParameter("altContactNo");
			String emailId = request.getParameter("emailId");
			String confirmEmail = request.getParameter("confirmEmail");
			String buildingName = request.getParameter("buildingName");
			String wingName = request.getParameter("wingName");
			String floorNo = request.getParameter("floorNo");
			String flatNo = request.getParameter("flatNo");
			String position = request.getParameter("position");
			String adharNo = request.getParameter("adharNo");
			String panNo = request.getParameter("panNo");
			String sba = request.getParameter("sba");
			
		
		 	HibernateUtility hbu = HibernateUtility.getInstance();
			Session session = hbu.getHibernateSession();
			Transaction transaction = session.beginTransaction();
		
			MemberDetailsHibernate mdh = (MemberDetailsHibernate) session.get(MemberDetailsHibernate.class,new Long(fkMemberId));
				
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				Date dateOfBirth = null;
				
				try{
					dateOfBirth = format.parse(dob);
					mdh.setDob(dateOfBirth);
					
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("Exception in date parsing");
				}
				
				
				mdh.setFirstName(firstName);
				mdh.setMiddleName(middleName);
				mdh.setLastName(lastName);
				mdh.setContactNo(Long.parseLong(contactNo));
				mdh.setAltContactNo(altContactNo);
				mdh.setEmailId(emailId);
				mdh.setConfirmEmail(confirmEmail);
				mdh.setBuildingName(buildingName);
				mdh.setWingName(wingName);
				mdh.setFloorNo(floorNo);
				mdh.setFlatNo(flatNo);
				mdh.setPosition(position);
				mdh.setAdharNo(Long.parseLong(adharNo));
				mdh.setPanNo(panNo);
				mdh.setSba(Long.parseLong(sba));
				
				session.update(mdh);
				transaction.commit();		
	}
	
	
		// get Member Report Building Wise
		public List getMemberReportBuildingWise(HttpServletRequest request, HttpServletResponse response)
		{
			String buildingName = request.getParameter("buildingName");
			
			Map<Long, MemberDetailsBean> map = new HashMap<Long, MemberDetailsBean>();
	
			MemberDetailsDao dao = new MemberDetailsDao();
			List<MemberDetailsBean> exp1List = dao.getMemberReportBuildingWise(buildingName);
	
			return exp1List;
	
		}
	
		
		// get Member Report Wing Wise
		public List getMemberReportWingWise(HttpServletRequest request, HttpServletResponse response)
		{
			String wingName = request.getParameter("wingName");
			
			Map<Long, MemberDetailsBean> map = new HashMap<Long, MemberDetailsBean>();
	
			MemberDetailsDao dao = new MemberDetailsDao();
			List<MemberDetailsBean> exp1List = dao.getMemberReportWingWise(wingName);
	
			return exp1List;
	
		}
		
		
		
		

		
	
}

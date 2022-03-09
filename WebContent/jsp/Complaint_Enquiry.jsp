<%@page import="com.society.bean.Complaint_EnquiryDetailsBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.society.hibernate.Complaint_EnquiryHibernate"%>
<%@page import="com.society.dao.Complaint_EnquiryDao"%>
<%@page import="java.util.Date"%>

<%@page import="com.society.hibernate.MemberDetailsHibernate"%>
<%@page import="com.society.dao.MemberDetailsDao"%>

<% boolean isHome=false;%>
<%@include file="commons/header.jsp"%>

<html>
<head>
		<script type="text/javascript" src="/society/staticContent/js/jquery-1.12.3.min.js"></script>
		<link rel="stylesheet" href="/society/staticContent/css/jquery-ui.min.css">
		<link rel="stylesheet" href="/society/staticContent/css/ui.jqgrid.css">
		<script type="text/javascript" src="/society/staticContent/js/jquery.min.js"></script>
		<script type="text/javascript" src="/society/staticContent/js/jquery-ui-min.js"></script>
		<script type="text/javascript" src="/society/staticContent/js/jquery-ui.js"></script>
		<script type="text/javascript" src="/society/staticContent/js/jqueryUi.js"></script>
		<script type="text/javascript" src="/society/staticContent/js/jquery.jqgrid.min.js"></script>    
        <script src="/society/staticContent/js/complaint_enquiry.js"></script>
        
        <script type="text/javascript">
          		 function complaint_EnquiryList()
          		 {
          		 	window.location = "Complaint_EnquiryList.jsp";
          		 }
          		</script> 
        
        
        <script type="text/javascript">
          		 function clientResponseList()
          		 {
          		 	window.location = "clientResponseList.jsp";
          		 }
          		</script> 
          		
          		<script type="text/javascript">
					function editClientEnquiry()
					{
						window.location = "EditClientEnquiry.jsp";

					}

          		</script>
          		
          		<script type="text/javascript">
					function clientResponseFollowUpList()
					{
						window.location="Complaint_EnquiryFollowUpList.jsp";
					}

          		</script>
          		  		
          		  		
     	<script type="text/javascript">
			function isNumber(evt) {
		    evt = (evt) ? evt : window.event;
		    var charCode = (evt.which) ? evt.which : evt.keyCode;
		    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		        return false;
		    }
		    return true;
		}
			</script>
		
		<script type="text/javascript">
			function isAlphabets(evt) {
		    evt = (evt) ? evt : window.event;
		    var charCode = (evt.which) ? evt.which : evt.keyCode;
		    if ((charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) {
		        return false;
		    }
		    return true;
			}


		</script>
		
		<script type="text/javascript">
			function isAlphabetsWithSpace(evt) {
		    evt = (evt) ? evt : window.event;
		    var charCode = (evt.which) ? evt.which : evt.keyCode;
		    if (charCode!=32 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122)) {
		        return false;
		    }
		    return true;
			}


		</script>
	<%
        Long lastComplaintNo = 1l;
	 %>
	 <%
	    Complaint_EnquiryDao data = new Complaint_EnquiryDao();
		List stkList  = data.getLastComplaintNo();
		for(int i=0;i<stkList.size();i++)
		{
			Complaint_EnquiryDetailsBean st = (Complaint_EnquiryDetailsBean)stkList.get(i);
			lastComplaintNo = st.getLastComplaintNo();
			lastComplaintNo++;
		}      
     %>
		
	<style>
		.leftmanu
		{
		    width: 190px;
		    background: #c50000a6;
		    color:  white;
		}
	</style>

</head>
<body>
<div class="container-fluid" > 
<div class="row" style="min-height:300px;">
    <div  class="col-md-12">
        <h3>Left Tabs</h3>
        <hr/>
        <div class="col-md-2">
            <ul class="nav nav-tabs">
                <li class=""><a class="leftmanu" href="#enquiry" data-toggle="tab">Complaint/Enquiry</a></li>
                <li><a class="leftmanu" href="#response" data-toggle="tab">Follow Up</a></li>
            </ul>
        </div>
				<div class="col-xs-9">
					<!-- Tab panes -->
					<div class="tab-content">
					
					<!---------Complaint Enquiry ---------->
<div class="tab-pane active" id="enquiry">
  <form class="form-horizontal" method="post" action="" name="clientEnqForm">
   	    	 <div class="row">
			    <div align="center">
			  		<h2 class="form-name style_heading">Complaint/Enquiry Details</h2>
			  	</div>
			  	
			  	<div class="row">
					<div class="form-group" align="right">
						<div class="col-sm-offset-6 col-md-5 control-label">
							<div style="font-weight: bold;">
								<p style="font-size: 20px;">Complaint No: <%=lastComplaintNo%></p>
							</div>
						</div>
					</div>
				</div>
			  	
		     <div class="row">
				     <div class="col-sm-offset-1 col-md-10">
						  <hr style="border-top-color:#c1b1b1;">
				     </div>	
		   		 </div>
		    </div>
				<%
					SimpleDateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd");
					String todayDate = simpleformat.format(new Date());
				%>

		<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Name:<sup>*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span>
							<%
								MemberDetailsDao sdd33 = new MemberDetailsDao();
								List sList44 = sdd33.getAllMemberForCashBook();
							%>
							<input list="memeberNameList" id="fk_member_id" class="form-control" onchange="getDetailsOfMwmber()" onfocus="this.value=''">
							<datalist id="memeberNameList">
								<%
									for (int i = 0; i < sList44.size(); i++) {
										MemberDetailsHibernate sup = (MemberDetailsHibernate) sList44.get(i);
								%>

								<option data-value="<%=sup.getPkMemId() %>"
								value="<%=sup.getFirstName()%> <%=sup.getLastName()%> <%=sup.getContactNo()%>">

									<%
										}
									%>
								
							</datalist>
							
							 <!-- <input type="text" id='firstName' name="firstName" onkeypress="return isAlphabets(event)"
								class="form-control" placeholder="Enter Name"/> -->
						</div>
					</div>


					<div class="col-sm-2" align="right">
						<label class="control-label">Date:<sup>*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>
							 <input type="date" id='todayDate' name="todayDate" value=<%=todayDate %> class="form-control">
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Wing Name:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='wingName' name="wingName" readonly="readonly"
								class="form-control" placeholder="Wing Name">
						</div>
					</div>

					<div class="col-sm-2" align="right">
						<label class="control-label">Building Name:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> 
							<i class="glyphicon glyphicon-hand-right"></i>
							</span>
                            <input type="text" id='buildingName' name="buildingName" class="form-control" readonly="readonly" placeholder="Building Name" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Floor No:</label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='floorNo' name="floorNo" readonly="readonly"
								class="form-control" placeholder="Floor No">
						</div>
					</div>
					
				<div class="col-sm-2" align="right">
						<label class="control-label">Flat No:</label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> 
							<input type="text" id='flatNo' readonly="readonly" name='flatNo'
								class="form-control" placeholder="Flat No">
						</div>
					</div>
				</div>
			</div>
			
			
			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Type:<sup>*</sup></label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> 
							
							<select class="form-control" id="type" name="name">
								<option value="">--select--</option>
								<option value="request">Request</option>
								<option value="complaint">Complaint</option>
								<option value="enquiry">Enquiry</option>
								<option value="feedback">Feedback</option>
								
							</select>
							
						</div>
					</div>
					
					<div class="col-sm-2" align="right">
						<label class="control-label">Email:</label>
					</div>
						<div class="col-md-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-envelope"></i>
								</span> 
								<input type="text" id='email' name='flatNo' class="form-control" placeholder="Enter email">
							</div>
						</div>
			
				</div>
			</div>
			
			
			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Description:<sup>*</sup></label>
					</div>
					<div class="col-md-8">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							 <!-- <input type="text" id='floorNo' name="floorNo" readonly="readonly" class="form-control" placeholder="Floor No"> -->
						<textarea id="description" rows="3" cols="20" class="form-control" placeholder="Enter Description"></textarea>
						</div>
					</div>

				</div>
			</div>

<!-- 			
			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Business Name:<sup>*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-pencil"></i>
							</span> <input type="text" id='businessName' name="businessName" 
								class="form-control" placeholder="Enter Business Name">
						</div>
					</div>

					<div class="col-sm-2" align="right">
						<label class="control-label">Business Address:<sup>*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> 
							<i class="glyphicon glyphicon-home"></i>
							</span>
							<textarea id='businessAddress' name="businessAddress" class="form-control" placeholder="Enter Business Address" rows="3" cols="15"></textarea>
							
                            <input type="text" id='businessAddress' name="businessAddress" class="form-control" placeholder="Enter Business Address" />
						</div>
					</div>
				</div>
			</div>
		
		<div class="row">
				<div class="form-group">

							
				<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Email:<sup>*</sup></label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-envelope"></i>
							</span> <input type="text" id='emailId' name="emailId"
								class="form-control" placeholder="Enter Email Id">
						</div>
					</div>
					
				<div class="col-sm-2" align="right">
						<label class="control-label">Product Name:<sup>*</sup></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='productName' name="productName" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter Product Name">
						</div>
					</div>
					


				</div>
			</div>
			
			
			
			<div class="row">
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">City:</label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='city' name="city" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter City">
						</div>
					</div>
					
					<div class="col-sm-2" align="right">
						<label class="control-label">State:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='state' name="state" onkeypress="return isAlphabets(event)"
								class="form-control" placeholder="Enter State">
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group">
					
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Country:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='country' name="country" onkeypress="return isAlphabets(event)"
								class="form-control" placeholder="Enter Country">
						</div>
					</div>
					
				<div class="col-sm-2" align="right">
						<label class="control-label">Zip Code:</label>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='zipcode' name="zipcode" maxlength="6" onkeypress="return isNumber(event)"
								class="form-control" placeholder="Enter Zip Code">
						</div>
					</div>
					
					
				</div>
			</div>
			
			
			<div class="row">
			 <div class="form-group">
						
					<div class="col-sm-2 col-sm-offset-1" align="right">
						<label class="control-label">Reference By:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span> <input type="text" id='referenceBy' name="referenceBy"
								class="form-control" placeholder="Enter Reference By">
						</div>
					</div>

			
					
					<div class="col-sm-2" align="right">
						<label class="control-label">Comment:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='comment' name="comment"
								class="form-control" placeholder="Enter Comment">
						</div>
					</div>
				</div>
			</div>
			
				
			<div class="row">
				<div class="form-group">

			
			<div class="col-sm-2 col-sm-offset-1" align="right">
                 <label class="control-label">Upload PDF File:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon">
							<i class="glyphicon glyphicon-pencil"></i>
							</span>
						  <input type="file" id='uploadFile' name="uploadFile" class="form-control" placeholder="upload File .pdf">
					 	</div>
					 </div>
				</div>
			</div>
			
			
		 -->

			<div class="container" align="center">
				<input type="button" class="btn btn-success btn-lg btnn" value="Save" name="btn" onclick="validationAddMemberComplaint_EnquiryDetails()"> 
				<input type="button" class="btn btn-danger btn-lg btnn" value="Cancel" onclick="reset()">
				<input type="button" value="List" id="listBtn" class="btn btn-primary btn-lg btnn" onclick="complaint_EnquiryList()">
			
			<!-- 	<input type="button" value="List" id="listBtn" class="btn btn-primary btn-lg btnn" onclick="clientEnquiryList()">
				<input type="button" value="Edit" id="listBtn" class="btn btn-primary btn-lg btnn" onclick="editClientEnquiry()">
			 -->	<!-- <input type="button"  value="Edit" id="listBtn" class="btn btn-primary btn-lg btnn" onclick="edit()"> -->
			
			</div>
		</form>
	</div>


	
	  <!----------------Member Follow Up Response ------------>
       <div class="tab-pane" id="response">
		<form class="form-horizontal" method="post" action="" name="clientResponseForm">
			<div class="row">
			    <div align="center">
			  		<h2 class="form-name style_heading">Follow Up</h2>
			  	</div>
		     <div class="row">
				     <div class="col-sm-offset-1 col-md-10">
						<hr style="border-top-color:#c1b1b1;">
				     </div>	
		   		 </div>
		    </div>
		    
		    
 		<div class="row form-group">
		<label class="col-md-2 control-label" for="employeename" style="text-align:  right;">Name :<sup>*</sup>
					</label>
					<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span>	
							<%
								Complaint_EnquiryDao dao = new Complaint_EnquiryDao();
								List list = dao.getAllMemberNames();
							%>
							<input list="memberNameList2" id="fk_member_id2" class="form-control" onchange="getComplaintNoAgaistMemberId()">
							<!-- <input list="memberNameList2" id="fk_member_id2" class="form-control" onchange="getDescriptionOfMember(); getComplaintNoAgaistMemberId()"> -->
							<datalist id="memberNameList2">
							<%
								for (int i = 0; i < list.size(); i++)
								{
									Complaint_EnquiryHibernate sup = (Complaint_EnquiryHibernate) list.get(i);
							%>

							<option data-value="<%=sup.getFkMemberId() %>" value="<%=sup.getMemberName()%>">
							<%
								}
							%>								
							</datalist>
						</div>
					</div>
		
							<div class="col-sm-2" align="right">
							<label class="control-label">Complaint Number:<sup>*</sup></label>
						</div>
						<div class="col-sm-3">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-hand-right"></i>
								</span>
							<select class="form-control" id="complaintNo" name="complaintNo" onchange="getDescriptionOfMember()"></select>
							</div>
						</div>
       			</div> 
		
<!-- 		<div class="row form-group">
		
			<label class="col-md-2 control-label" for="employeename" style="text-align:  right;">Contact Person Name :</label>
						<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-user"></i>
							</span>
							 <input type="text" id='contactPersonName' name="contactPersonName" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter Contact Person Name" />
						</div>
					</div>



					<div class="col-sm-2" align="right">
						<label class="control-label">Product Name:<sub>*</sub></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span> <input type="text" id='productName1' name="productName1" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter Product Name">
						</div>
					</div>
				
			</div>
			 -->
			 
			 
			 
			 <div class="row form-group">
		
			<label class="col-md-2 control-label" for="employeename" style="text-align:  right;">Date :</label>
						<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>							
							<!--
								<input list="typeList_Drop" id="typeForFollwUp" class="form-control">
								<datalist id="typeList_Drop"></datalist>
							 -->
							 
							<input type="date" id='todayDate2' name="todayDate2" value="<%=todayDate %>" class="form-control"/>
							<!-- <datalist id="typeList_Drop"></datalist> -->
							
							
							 <!-- <input type="text" id='contactPersonName' name="contactPersonName" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter Contact Person Name" /> -->
						</div>
					</div>



					<div class="col-sm-2" align="right">
						<label class="control-label">Email:</label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							 <input type="text" id='emailId' name="emailId" readonly="readonly"
								class="form-control" placeholder="Email">
						</div>
					</div>
				
			</div>
			 
			 
			 
			 <div class="row form-group">
		
			<label class="col-md-2 control-label" for="employeename" style="text-align:  right;">Type :</label>
						<div class="col-md-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							
							<!--
								<input list="typeList_Drop" id="typeForFollwUp" class="form-control">
								<datalist id="typeList_Drop"></datalist>
							 -->
							 
							<input type="text" id="typeForFollwUp" class="form-control" readonly="readonly">
							<!-- <datalist id="typeList_Drop"></datalist> -->
							
							
							 <!-- <input type="text" id='contactPersonName' name="contactPersonName" onkeypress="return isAlphabetsWithSpace(event)"
								class="form-control" placeholder="Enter Contact Person Name" /> -->
						</div>
					</div>



					
				
			</div>
			 
			 
			
			<div class="row form-group">
				<label class="col-md-2 control-label" style="text-align: right;">Description:<sup>*</sup></label>
				<div class="col-md-8">
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-hand-right"></i></span>
					<textarea id="description2" rows="2" readonly="readonly" cols="15" placeholder="Description" class="form-control"></textarea>
						
				</div>			
			</div>
			
<!-- 			<div id="followUpDate">
					<div class="col-sm-2" align="right">
						<label class="control-label">Status:<sub>*</sub></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							
							<select class ="form-control" id="status" name="status">
								<option value="">--Select--</option>
								<option value="notAssigned">Not Assigned</option>
								<option value="assigned">Assigned</option>
								<option value="enquiryInProgress">Enquiry in progress</option>
								<option value="complete">Complete</option>
							</select>
							 
						</div>
					</div>
			
			</div>  -->
			
			
			<script type="text/javascript">

			$(document).ready(function(){

				$('#clientResponse').change(function(){
					$(this).find("option:selected").each(function(){
						if($(this).attr("value")=="Followup"){
								$('#followUpDate').show();
							}

						else
							{
								$('#followUpDate').hide();
							}
					});
				}).change();
			});
				

			</script>
			</div>
	
	
	
			
			  <div class="row form-group">
				<div class="col-sm-2" align="right">
						<label class="control-label">Status:<sub>*</sub></label>
					</div>
					<div class="col-sm-3">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							
							<select class ="form-control" id="status" name="status">
								<option value="">--Select--</option>
								<option value="notAssigned">Not Assigned</option>
								<option value="assigned">Assigned</option>
								<option value="enquiryInProgress">Enquiry in progress</option>
								<option value="complete">Complete</option>
							</select>
							 
						</div>
					</div>
			
			</div>
	
	

				<div class="row">
					<div class="form-group" style=" margin-left: -3px;">
						<div class="col-md-2" align="right">
						
							<label class="control-label">Response Detail:</label>
						</div>
						<div class="col-md-8">
						<div class="input-group">
						
						<span class="input-group-addon"> <i
								class="glyphicon glyphicon-hand-right"></i>
							</span>
							<textarea id="responseDetails" rows="2" cols="15" placeholder="Enter Response Details" class="form-control"></textarea>
						</div>
						</div>
					</div>
				</div>

				<div class="container" align="center" style=" margin-left: -100px;">
				<input type="button" id="save" class="btn btn-success btn-lg btnn" value="Save" name="btn2" onclick="validationAddComplaint_EnquiryFollowUpDetails()"> 
				<input type="button" class="btn btn-danger btn-lg btnn" value="Cancel" name="btn2" onclick="location.reload()">
				<input type="button" value="List" id="listBtn123" class="btn btn-primary btn-lg btnn" onclick="clientResponseFollowUpList()">
				
			</div>
      </form>
      
		</div>
   </div>
  </div> 
 </div>
</div>
        <%@include file="commons/newFooter.jsp" %> 	
</div>
</body>
</html>		
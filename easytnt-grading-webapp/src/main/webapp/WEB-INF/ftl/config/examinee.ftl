<div class="subject-container container-fluid" >
  <div class="col-md-12" style="padding-right:0px;padding-left:0px;">  
     <form id="uploadForm"  method="POST" action="" enctype="multipar/form-data" class="form-horizontal">
      <div class="row">
      	<div class="form-group">
      	  <label for="inputEmail3" class="col-sm-2 control-label">选择文件</label>
      	  <div class="col-sm-9">
			     <div class="input-group file-preview">
				      <input type="text" class="col-sm-2 form-control file-preview-filename" disabled="disabled">
				      <div class="input-group-btn"> 
					    <button type="button" class="btn btn-default file-preview-clear" style="display:none;">
					      <span class="glyphicon glyphicon-remove">清除</span>
					    </button>
					    <div class="btn btn-default file-preview-input" >
					      <span class="glyphicon glyphicon-folder-open"></span>
					      <span class="file-preview-input-title">选择文件</span>
					      <input id="fileName" type="file" name="fileName" style="display:none;">
					    </div>
					    <button type="type" class="btn btn-default " id="upload">
					      <span class="glyphicon glyphicon-upload"></span>上传
					    </button>
					  </div>
				    </div>
		 </div>
		 </div>
      </div>
     <div class="row">
	   	<div class="form-group">
		    <label for="StringOne" class="col-sm-2 control-label">学籍号</label>
		    <div class="col-sm-4">
		        <select  name="studentNumber">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringTwo" class="col-sm-1 control-label">学生姓名</label>
		    <div class="col-sm-5">
		       <select name="studentName">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringThere" class="col-sm-2 control-label">性别</label>
		    <div class="col-sm-4">
		       <select name="gender">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringFour" class="col-sm-1 control-label">民族</label>
		    <div class="col-sm-5">
		       <select name="nation">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringFive" class="col-sm-2 control-label">出生日期</label>
		    <div class="col-sm-4">
		       <select name="birthday">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringSix" class="col-sm-1 control-label">座位号</label>
		    <div class="col-sm-5">
		       <select name="seatingNumber">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">准考证号</label>
		    <div class="col-sm-4">
		       <select name="examinneUuid">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">身份证</label>
		    <div class="col-sm-5">
		       <select name="uuidType">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	   <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">文理科标志</label>
		    <div class="col-sm-4">
		       <select name="arts">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">班级名称</label>
		    <div class="col-sm-5">
		       <select name="clazzName">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">班级代码</label>
		    <div class="col-sm-4">
		       <select name="clazzCode">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">缺考标志</label>
		    <div class="col-sm-5">
		       <select name="absence">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">总分</label>
		    <div class="col-sm-4">
		       <select name="totalScore">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">考场编号</label>
		    <div class="col-sm-5">
		       <select name="roomNumber">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">学校名称</label>
		    <div class="col-sm-4">
		       <select name="schoolName">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">学校代码</label>
		    <div class="col-sm-5">
		       <select name="schoolCode">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-2 control-label">行政区编号</label>
		    <div class="col-sm-4">
		       <select name="districtNumber">
				    <option>全部</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">行政区名称</label>
		    <div class="col-sm-5">
		       <select name="districtName">
				    <option>全部</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  </form>
  </div>
  <hr style="margin-top:10px;margin-bottom:10px;">
  <table class="table table-striped table-bordered ">
  	<thead class="bg-primary">
  	  <tr>
  	    <th>字段1</th>
  	    <th>字段2</th>
  	    <th>字段3</th>
  	    <th>字段4</th>
  	    <th>字段5</th>
  	    <th>字段6</th>
  	    <th>字段7</th>
  	    <th>字段8</th>
  	  </tr>
  	</thead>
  	<tbody>
  	</tbody>
  </table>
  <div class="col-sm-offset-5 col-md-4" style="margin-bottom:10px;">
    <button type="button"  class="import btn btn-default">导入考生</button>
  </div>
  <#import "/taglib/commons/pager.ftl" as page > 
  <@page.pager pager=query css="margin:0;text-align:center;"/>
  <#import "/taglib/commons/pager.ftl" as page> 
  <@page.pager pager=query/>
</div>
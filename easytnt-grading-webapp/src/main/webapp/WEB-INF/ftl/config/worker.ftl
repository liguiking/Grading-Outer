<div class="subject-container">
  <table class="table table-striped table-bordered ">
  	<thead class="bg-primary">
  	  <tr>
  	    <th>科目</th><th>姓名</th><th>账号</th><th>操作</th>
  	  </tr>
  	</thead>
  	<tbody>
  	 <#list result as t>
  	  <tr >
  	    <td><a href="#" data-rr-name="subjectName" data-rr-value="">${t.subject.name}</a></td>
  	    <input type="hidden" id="teacherId" value="${t.teacherId}"/>
  	    <td>${t.teacherName}</td>
  	    <td><a href="#">1001</a></td>
  	    <td><a href="#">修改密码</a>|<a href="#">分配任务</a></td>
  	  </tr>
  	  </#list>
  	  <tr class="bg-warning">
  	    <td><a href="#" id="newTeacher"><i class="icon-plus"></i></a></td>
  	    <td></td>
  	    <td><a href="#"></a></td>
  	    <td><a href="#"></td>
  	  </tr>
  	</tbody>
  </table>
  
  <div class="subject-editor">
	<div class="col-md-4"></div>
	<form class="form-horizontal col-md-4">
	  <div class="form-group">
	    <label for="teacher" class="col-sm-4 control-label">科目名称</label>
	    <div class="col-sm-8">
	    <p></p>
	    <select id="subject">
	     <#list result as teacher>
	    	<option value="${teacher.subject.id}">${teacher.subject.name}</option>
	     </#list>    
	    </select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="teacherName" class="col-sm-4 control-label">姓名</label>
	    <div class="col-sm-8">
	      <input type="text" class="form-control" id="teacherName" id="teacherName" placeholder="姓名">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="teacherAccount" class="col-sm-4 control-label">账号</label>
	    <div class="col-sm-8">
	      <input type="text" readonly="readonly" class="form-control" id="teacherAccount"  placeholder="账号">
	    </div>
	  </div>  	  
	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-9">
	      <button type="submit" class="btn btn-default">保存</button>
	    </div>
	  </div>
	</form>
	<div class="col-md-4"></div>
  </div>	
</div>
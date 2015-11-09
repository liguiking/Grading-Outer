<div class="subject-container">
  <table class="table table-striped table-bordered ">
  	<thead class="bg-primary">
  	  <tr>
  	    <th>科目</th><th>姓名</th><th>账号</th><th>操作</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <tr >
  	    <td><a href="#" data-rr-name="subjectName" data-rr-value="100">语文</a></td>
  	    <td >小红</td>
  	    <td><a href="#">100001</a></td>
  	    <td><a href="#">修改密码</a>|<a href="#">分配任务</a></td>
  	  </tr>
  	  <tr >
  	    <td><a href="#" data-rr-name="subjectName" data-rr-value="102">数学</a></td>
  	    <td>小兰</td>
  	    <td>100002</td>
  	    <#--<i class=" icon-remove"></i>-->
  	    <td><a href="#">修改密码</a>|<a href="#">分配任务</a></td>
  	  </tr>  	    	
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
	     <#--<#list result as subject> -->
	    	<option value="100">语文<#--${subject.name}--></option>
	    	<option value="101">英语</option>
	    	<option value="102">数学</option>
	    	<option value="103">科学</option>
	     <#--</#list> -->	    
	    </select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="teacherName" class="col-sm-4 control-label">姓名</label>
	    <div class="col-sm-8">
	      <input type="text" class="form-control" id="teacherName" data-rr-type="number" placeholder="姓名">
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
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
  	    <td>小红</td>
  	    <td><a href="#">100001</a></td>
  	    <td><a href="#">修改密码</a>|<a href="#">启用</a></td>
  	  </tr>
  	  <tr >
  	    <td><a href="#" data-rr-name="subjectName" data-rr-value="101">数学</a></td>
  	    <td>小兰</td>
  	    <td>100002</td>
  	    <#--<i class=" icon-remove"></i>-->
  	    <td><a href="#">修改密码</a>|<a href="#">启用</a></td>
  	  </tr>  	    	
  	  <tr class="bg-warning">
  	    <td><a href="#" id="newTeacher"><i class="icon-plus"></i></a></td>
  	    <td>未知</td>
  	    <td><a href="#">设计账号中</a></td>
  	    <td><a href="#">修改密码</a>|<a href="#">启用</a></td>
  	  </tr>
  	</tbody>
  </table>
  
  <div class="subject-editor">
	<div class="col-md-4"></div>
	<form class="form-horizontal col-md-4">
	  <div class="form-group">
	    <label for="subjectName" class="col-sm-4 control-label">科目名称</label>
	    <div class="col-sm-8">
	    <p></p>
	    <select style="width:205px;" id="subjectId">
	     <#--<#list result as subject> -->
	    	<option value="一年级语文">一年级语文<#--${subject.name}--></option>
	    	<option value="二年级数学">二年级数学</option>
	    	<option value="三年级自然">三年级自然</option>
	    	<option value="七年级英语">七年级英语</option>
	     <#--</#list> -->	    
	    </select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="teacherName" class="col-sm-4 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
	    <div class="col-sm-8">
	      <input type="text" class="form-control" id="teacherName" data-rr-type="number" placeholder="姓名">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="teacherAccount" class="col-sm-4 control-label">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
	    <div class="col-sm-8">
	      <input type="text" readonly="readonly" class="form-control" id="teacherAccount" data-rr-type="number" placeholder="账号">
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
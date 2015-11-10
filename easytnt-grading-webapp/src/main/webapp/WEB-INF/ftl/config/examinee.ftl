<div class="subject-container">
  <form id="uploadForm"  method="POST" action="" enctype="multipar/form-data" class="form-inline">
    <div class="col-md-offset-8 col-md-4 input-group file-preview">
      <input type="text" class="form-control file-preview-filename" disabled="disabled">
      <div class="input-group-btn"> 
	    <button type="button" class="btn btn-default file-preview-clear" style="display:none;">
	      <span class="glyphicon glyphicon-remove"></span>清除
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
  </form>
  <hr style="margin-top:10px;margin-bottom:10px;">
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
</div>
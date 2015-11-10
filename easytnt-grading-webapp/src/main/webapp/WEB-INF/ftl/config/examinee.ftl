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
  	    <th>科目</th><th>姓名</th><th>操作</th>
  	  </tr>
  	</thead>
  	<tbody>
  	  <tr >
  	    <td>
  	      <select name="s">
  	        <option value="1">考点</option>
  	        <option value="2">考场</option>
  	        <option value="3">考生姓名</option>
  	        <option value="4">准考证号</option>
  	      </select>
  	    </td>
  	    <td>
  	      <select name="s">
  	        <option value="1">考点</option>
  	        <option value="2">考场</option>
  	        <option value="3">考生姓名</option>
  	        <option value="4">准考证号</option>
  	      </select>  	    
  	    </td>
  	    <td><a href="#"><i class="icon-plus"></i></a></td>
  	  </tr>
  	  
  	</tbody>
  </table>
  <nav>
    <ul class="pagination">
      <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
      <li><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
    </ul>
  </nav>  
</div>
<div class="subject-container container-fluid" >
  <div class="col-md-12" style="padding-right:0px;padding-left:0px;">  
     <form id="uploadForm"  method="POST" action="" enctype="multipar/form-data" class="form-horizontal">
      <div class="row">
      	<div class="form-group">
      	  <label for="inputEmail3" class="col-sm-1 control-label">选择文件</label>
      	  <div class="col-sm-11">
			     <div class="input-group file-preview">
				      <input type="text" class="form-control file-preview-filename" disabled="disabled">
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
		    <label for="StringOne" class="col-sm-1 control-label">字段1</label>
		    <div class="col-sm-5">
		        <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		    <label for="StringTwo" class="col-sm-1 control-label">字段2</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringThere" class="col-sm-1 control-label">字段3</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		    <label for="StringFour" class="col-sm-1 control-label">字段4</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringFive" class="col-sm-1 control-label">字段5</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		    <label for="StringSix" class="col-sm-1 control-label">字段6</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		 </div>
	  </div>
	  <div class="row">
	   	<div class="form-group">
		    <label for="StringSeven" class="col-sm-1 control-label">字段7</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
				 </select>
		    </div>
		    <label for="StringEight" class="col-sm-1 control-label">字段8</label>
		    <div class="col-sm-5">
		       <select  class="form-controller selectpicker">
				    <option value="-1">全部</option>
				    <option value="0">excel字段1</option>
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
  <#import "/taglib/commons/pager.ftl" as page > 
  <@page.pager pager=query css="margin:0;text-align:center;"/>
  <#import "/taglib/commons/pager.ftl" as page> 
  <@page.pager pager=query/>
</div>
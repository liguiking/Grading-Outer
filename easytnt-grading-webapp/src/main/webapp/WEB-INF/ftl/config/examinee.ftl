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
      <#list titleMap?keys as s> 
        <#if s_index ?? && s_index%2 = 0>
        <div class="row">
   		  <div class="form-group">
      	 	<label for="StringOne" class="col-sm-2 control-label">${titleMap[s]}</label>
		    <div class="col-sm-4">
		        <select  name="dataName" data-name="${s}">
				    <option>无</option>
				 </select>
		    </div>
      	 	<#else>
      	 	<label for="StringTwo" class="col-sm-1 control-label">${titleMap[s]}</label>
		    <div class="col-sm-5">
		       <select name="dataName" data-name="${s}">
				    <option>无</option>
				 </select>
		    </div>
		 </div>
	  	</div>
      	</#if>
      </#list>
      <#if titleMap ? size%2 = 1>
      	</div>
	  </div>
      </#if>
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
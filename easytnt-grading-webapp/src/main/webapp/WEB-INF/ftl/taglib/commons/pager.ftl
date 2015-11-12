<#macro pager id="pager" pager={"curpage":"1","totalpage":"0","pagesize":"15","totalrows":"0"}>
<div style="text-align: center">
  <input type="hidden" id="pageNum" value="${(pager.curPage)!1}"> 
  <input type="hidden" id="pageCount" value="${(pager.totalPage)!0}">
  <input type="hidden" id="pageSize" value="${(pager.pageSize)!10}"> 
  <input type="hidden" id="totalRows" value="${(pager.totalRows)!0}">
  <div id="${id}"  class="pager" style="margin-top: 20px; text-align: center;"></div>
</div>
</#macro>
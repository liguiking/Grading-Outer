<#import "/taglib/html.ftl" as doc> 
<@doc.html entryjs="cuttingDefine/cuttingDefine" title="切割定义" css=["cutting/common","cutting/resize"]>
 <#import "/taglib/commons/navigation.ftl" as nav> 
  <@nav.navigation menus2=menus2/>
  
<#include "./CuttingDefineUI.ftl">
<#import "/taglib/commons/status.ftl" as footer> 
  <@footer.status>
    <ul class="pull-right"></ul>
  </@footer.status>
</@doc.html>
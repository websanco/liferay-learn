<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->

<h1>${Title.getData()}</h1>

<hr />

<p>${Content.getData()}</p>

<#if Imagem1j5.getData()?? && (Imagem1j5.getData() != "")>
	<img 
		alt="${Imagem1j5.getAttribute("alt")}" 
		class="text-center" 
		data-fileentryid="${Imagem1j5.getAttribute("fileEntryId")}" 
		src="${Imagem1j5.getData()}" 
  />
</#if>
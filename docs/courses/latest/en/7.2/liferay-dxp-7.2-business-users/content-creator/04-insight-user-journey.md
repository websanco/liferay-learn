# Module 4: Gain Insight on the User Journey

<div class="ahead">
<h4>Learning Objectives</h4>

<ul>
    <li>Learn about Forms in Liferay DXP 7.2</li>
    <ul>
        <li>How to add fields, rules, and verification</li>
        <li>How to view feedback received via Forms</li>
    </ul>
    <li>Understand how Analytics Cloud can be used to view the data gathered through Form feedback</li>
</ul>

<h4>Tasks to Accomplish</h4>
<ul>
    <li>Create a user feedback Form</li>
    <li>Create reusable Form field groups</li>
    <li>Add a widget to a page that allows users to fill out Forms</li>
    <li>View Form feedback</li>
</ul>

<h4>Exercise Prerequisites (1/2)</h4>
<ul>
    </ul>
	<li>Unzipped module exercise files in the following folder structure:</li>
	<ul>	
		<li>Windows: <code>C:\liferay</code></li>
		<li>Unix Systems: <code>[user-home]/liferay</code></li>
	</ul>
	<li>A Liferay DXP or CE 7.2 instance up and running</li>
	    <ul>    
        <li>If you have not started your instance yet, first, make sure you have downloaded Docker. Then, use the following commands to get and start the Liferay Docker Image:</li>
        <ul>
            <li><code>docker pull liferay/[product]:[version]</code></li>
            <li><code>docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]</code></li>
        </ul>
    <li> See available Liferay DXP and CE versions at: <a href="https://hub.docker.com/r/liferay/dxp/tags">https://hub.docker.com/r/liferay/dxp/tags</a>
    </ul>
    <li>The following site structure set up in your Liferay instance:</li>
    <ul>
        <li>Livingstone Hotels & Resorts</li>
        <li>Livingstone Loop (use the Intranet Site Template)</li>
    </ul>
    <li>The user Omar Miles (Screen Name: omar.miles, email: omar.miles@livingstone.com) created in Users and Organizations</li>
    <li>The livingstone-fjord-theme.war file installed from the App Manager in the Control Panel</li>
    <ul>
        <li>Use the prereq-imports folder located in your Course Module exercises folder to install the war file into your Liferay instance.</li>
    </ul>
</ul>
</div>

<div class="ahead">
<h4>Exercise Prerequisites (2/2)</h4>
    <li>The Luxury Hotel Location Site Template added to your Liferay instance from Control Panel → Sites → Site Templates.</li>
    <ul>
        <li>Use the prereq-imports folder located in your Course Module exercises folder to import the site-templates.lar file into your Liferay instance.</li>
    </ul>
</div>

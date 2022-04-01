# Module 3: Automate and Optimize Content Publication

<div class="ahead">
<h4>Learning Objectives</h4>

<ul>
    <li>Understand how to use Structures and Templates to create Web Content types and to provide consistent design to Web Content Articles</li>
    <li>Understand the relationship between Collections, Page Fragments, and Content Pages</li>
    <li>Learn how to use the Asset Publisher and Display Page Templates to display different types of Content</li>
    <li>Learn about Personalization Experience Management:</li>
    <ul>
        <li>User Segments</li>
        <li>Content Page Personalization</li>
        <li>Content Set Personalization</li>
    </ul>
</ul>

<h4>Tasks to Accomplish</h4>
<ul>
    <li>Create new Structures and use Templates to control Web Content display</li>
    <li>Import Page Fragments Collections and create editable Content Pages</li>
    <li>Create an automated self-publishing page using the Asset Publisher</li>
    <li>Create a Display Page Template for a Web Content Structure</li>
    <li>Create personalized Content Pages for different User Segments</li>
</ul>

<h4>Exercise Prerequisites (1/2)</h4>
<ul>
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
    <li>The livingstone-fjord-theme.war (located in your module exercises folder) uploaded and installed from the App Manager in the Control Panel</li>
</ul>
</div>

<div class="ahead">
<h4>Exercise Prerequisites (2/2)</h4>
<ul>
    <li>The following Page Templates added to the Global Site of your Liferay instance:</li>
    <ul>
        <li>Asset Publisher Page</li>
    </ul>
    <li>The following site structure set up in your Liferay instance:</li>
    <ul>
        <li>Livingstone Hotels & Resorts</li>
        <li>Livingstone Life (use the Community Site Template)</li>
    </ul>
    <li>The Site Content Creator Role added to the platform</li>
    <ul>
        <li>Use the prereq-imports folder located in your module exercises folder to import the LAR file into your Liferay instance</li>
    </ul>
    <li>The fiji-blog.lar file imported to Livingstone Life's Blogs section of Site Administration from your module exercises folder</li>
</ul>
</div>

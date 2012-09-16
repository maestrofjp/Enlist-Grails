<%@ page import="org.grails.plugins.quartz.TriggerState" %>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="bootstrap" />
        <title>Quartz Jobs</title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.countdown.css', plugin: 'quartz-monitor')}"/>
		<style>
			.clockdate { margin: 0}
			.clocktime { margin: 0 }
			.clocktime:before { content: " - "; }
		</style>
    </head>
    <body>
    	<div class="row-fluid">
    
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Scheduler</li>
					    <g:if test="${scheduler.isInStandbyMode()}">
					        <li>
					        	<a href="<g:createLink action="startScheduler"/>"><i class="icon-play"></i>Start</a>
					        </li>
					    </g:if>
					    <g:else>
					        <li>
					        	<a href="<g:createLink action="stopScheduler"/>"><i class="icon-pause"></i>Pause</a>
					        </li>
					    </g:else>
					    <li class="nav-header">Current Time</li>
						<li id="clock" data-time="${now.time}">${now}</li>
						<p style="clear:both;"/>
					</ul>
				</div>
			</div>
			
			<div class="span9">
    
    			<div class="page-header">
					<h1>Quartz Jobs</h1>
				</div>
				
				<g:if test="${flash.message}">
					<bootstrap:alert>${flash.message}</bootstrap:alert>
				</g:if>
				
				
				<table class="table table-striped">
					<thead>
						<tr>
							<g:sortableColumn property="name" title="Name" />
							
			                <g:if test="${grailsApplication.config.quartz.monitor.showTriggerNames}">
			                    <th>Trigger Name</th>
			                </g:if>
			                
			                <g:sortableColumn property="lastRun" title="Last Run" />
			                
			                <th>Result</th>
			                
			                <g:sortableColumn property="nextFireTime" title="Next Scheduled Run" />
			                
			                <th>Actions</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
				        <g:each in="${jobs}" status="i" var="job">
				            <tr>
				                <td>${job.name}</td>
				                
				                <g:if test="${grailsApplication.config.quartz.monitor.showTriggerNames}">
				                    <td>${job.trigger?.name}</td>
				                </g:if>
				                
				                <g:set var="tooltip">${job.duration >= 0 ? "Job ran in: " + job.duration + "ms" : (job.error ? "Job threw exception: " + job.error : "")}</g:set>
				                <td class="quartz-tooltip quartz-status ${job.status?:"not-run"}" data-tooltip="${tooltip}">${job.lastRun}</td>
				                <td class="quartz-to-hide">${tooltip}</td>
				                
				                <g:if test="${scheduler.isInStandbyMode() || job.triggerStatus == TriggerState.PAUSED}">
				                    <td class="hasCountdown countdown_amount">Paused</td>
				                </g:if>
				                <g:else>
				                    <td data-next-run="${job.trigger?.nextFireTime?.time ?: ""}">${job.trigger?.nextFireTime}</td>
				                </g:else>

								<%-- TODO: Update icons with Bootstrap glyphs --%>				                
				                <td>
				                    <g:if test="${job.status != 'running'}">
				                        <g:if test="${job.trigger}">
				                            <a href="<g:createLink action="stop" params="[jobName:job.name, triggerName:job.trigger.name, triggerGroup:job.trigger.group]"/>"><img class="quartz-tooltip" data-tooltip="Stop job from running again" src="<g:resource dir="images" file="stop.png" plugin="quartz-monitor"/>"></a>
				                            <g:if test="${job.triggerStatus == TriggerState.PAUSED}">
				                                <a href="<g:createLink action="resume" params="[jobName:job.name, jobGroup:job.group]"/>"><img class="quartz-tooltip" data-tooltip="Resume job schedule" src="<g:resource dir="images" file="resume.png" plugin="quartz-monitor"/>"></a>
				                            </g:if>
				                            <g:elseif test="${job.trigger.mayFireAgain()}">
				                                <a href="<g:createLink action="pause" params="[jobName:job.name, jobGroup:job.group]"/>"><img class="quartz-tooltip" data-tooltip="Pause job schedule" src="<g:resource dir="images" file="pause.png" plugin="quartz-monitor"/>"></a>
				                            </g:elseif>
				                        </g:if>
				                        <g:else>
				                            <a href="<g:createLink action="start" params="[jobName:job.name, jobGroup:job.group]"/>"><img class="quartz-tooltip" data-tooltip="Start job schedule" src="<g:resource dir="images" file="start.png" plugin="quartz-monitor"/>"></a>
				                        </g:else>
				                        <a href="<g:createLink action="runNow" params="[jobName:job.name, jobGroup:job.group]"/>"><img class="quartz-tooltip" data-tooltip="Run now" src="<g:resource dir="images" file="run.png" plugin="quartz-monitor"/>"></a>
				                    </g:if>
				                </td>
				            </tr>
				        </g:each>
					</tbody>
				</table>
			</div>
        </div>
        
            
        <g:unless test="${grailsApplication.config.quartz.monitor.showCountdown == false}">
            <g:javascript src="jquery.countdown.js" plugin="quartz-monitor"/>
            <g:javascript src="jquery.color.js" plugin="quartz-monitor"/>
        </g:unless>
        <g:unless test="${grailsApplication.config.quartz.monitor.showTickingClock == false}">
            <g:javascript src="jquery.clock.js" plugin="quartz-monitor"/>
        </g:unless>
        <g:javascript src="quartz-monitor.js" plugin="quartz-monitor"/>
        
        
    </body>
</html>
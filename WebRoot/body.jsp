<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
	#content-holder{
		width:900px;
		margin: 0 auto;
		padding-bottom: 20px;
		display: block;
		padding: 0;
	}
	#content{
		border-radius: 5px;
		display: block;
	}
	#publish{
		margin-bottom: 15px;
		z-index: 100;
		display: block;
		position: relative;
		height: 83px;
		
	}
	.blog-photo{
		float: left;
		display: block;
		margin: 0;
		padding: 0;
	}
	#photo-action{
		float: left;
		margin-left: 21px;
		width: 540px;
		display: block;
		border-bottom: 2px solid #B9BDC2;
		margin-top: 2px;
	}
	#pb-triangle {
		position: absolute;
		top: 25px;
		left:76px;
		width: 9px;
		height: 13px;
		background: url(./images/pushservice/triangle.png);
		display: block;
	}
	#pb-action {
		padding: 13px 5px 12px;
		background: white;
		border-radius: 7px;
		margin: 0;
		display: block;
		overflow: hidden;
	}
	.ph li{
		float: left;
		text-align: center;
		width: 100px;
		margin: 0;
		padding: 0;
	}
	.ph li a{
		display: block;
		width: 106px;
		height: 65px;
		background: url(./images/pushservice/wpmv.png) no-repeat 0 0;
		text-indent: -9999px;
		outline: none;
	}
	#main {
		float: left;
		width: 628px;
		display: block;
		margin: 0;
		pause: 0;
		background: #D3D9DE
	}
	.ph {
		position: relative;
		display: block;
		list-style: none;
		height: 65px;
		margin: 0;
		padding: 0;
	}
	#blog-avatar {
		display: block;
		width: 64px;
		height: 64px;
		background-position: center center;
		border-radius: 5px;
		text-indent: -9999px;
		outline: none;
		margin: 0;
		padding: 0;
	}
	#feed-list {
		background-color: #fff;
		border-radius: 7px;
		clear: both;
	}
</style>
<div id="content-holder">
	<div id="content">
		<div id="main">
			<div id="publish" class="pic">
				<div class="blog-photo">
					<a href="" id="blog-avatar" style="background-image: url(./images/pushservice/avatar.png);">cluo</a>
				</div>
				<div id="photo-action">
					<div id="pb-triangle"></div>
					<div id="pb-action">
						<ul class="ph" >
							<li><a href="">文字</a></li>
							<li><a href="" style="background-position: -106px 0;">图片</a></li>
							<li><a href="" style="background-position: -212px 0;">音乐</a></li>
							<li><a href="" style="background-position: -318px 0;">链接</a></li>
							<li><a href="" style="background-position: -424px 0;">视频</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="content-list">
			
			</div>
			<div id="friend-list" style="display: none;"></div>
			<div id="tumblelog-member" style="display: "></div>
			<div id="inboxblog" style="display: none;"></div>
			<div id="feed-list">
			<h2 id="main-title">
								服务条款
							</h2>
							<h4>
								点点网平台用户协议：
							</h4>
							<ol>
								<li>
									本协议适用于点点网开发的点点网平台。使用点点网平台以及与其相关联的各项技术服务和网络服务之前，您必须同意接受本协议。若不接受本协议，您将无法使用点点网平台及相关服务。
								</li>
								<li>
									您可以通过以下任何一种方式接受本协议：1) 在点点网平台任一用户界面中，点击表示接受或同意本协议的全部条款的选项；2)
									实际使用点点网平台及点点网提供的其他相关服务。您对点点网平台及其他相关服务的使用将被视为您自实际使用起便接受了本协议的全部条款。
								</li>
								<li>
									点点网网络平台的所有权和运营权归点点网所有，并保留随时变更平台提供的信息和服务的权利。点点网所提供的相关信息和服务的使用者（以下简称“用户”）在使用之前必须同意以下的所有条款。
								</li>
								<li>
									用户在点点网平台上发布的信息内容由用户及点点网共同所有，任何其他组织或个人未经用户本人授权同意，不得复制、转载、擅改其内容。用户不得在点点网平台发布和散播任何形式的含有下列内容的信息：1）为相关法律法规所禁止；2）有悖于社会公共秩序和善良风俗；3）损害他人合法权益；4）其他点点网认为不适当在本平台发布的内容。
									点点网保留删除和变更上述相关信息的权利。
								</li>
								<li>
									用户应保证在点点网平台的注册信息的真实、准确和完整，并在资料变更时及时更新相关信息。对于任何信息不实所导致的用户本人或第三方损害，点点网不承担任何责任。用户应妥善保管个人注册信息及登录密码等资料，用户将对以其注册用户名进行的所有活动和事件负法律责任。
								</li>
								<li>
									点点网非常强调保护用户的隐私。点点网将致力于为用户提供最可靠的隐私保护措施。未经用户的特别授权，点点网不会将用户信息公开或透露给任何第三方个人或机构，但在下列情形除外：1)
									根据司法机关、政府部门的强制命令提供涉及用户信息的相关资料； 2) 不可抗力与不可控因素导致的信息外泄； 3)
									点点网基于自身的合法维权需要而使用用户的相关信息。
								</li>
								<li>
									用户同意使用点点网平台服务所潜在的风险及其一切可能的后果将完全由自己承担，点点网对这些可能的风险和后果不承担任何责任。
								</li>
								<li>
									点点网不保证点点网平台提供的服务能够满足用户的所有要求，也不保证已存在的服务不会中断，对这些服务的及时性、安全性、准确性也不作保证。对于因系统维护或升级的需要而需暂停网络服务的情形，点点网将视具体情形尽可能事先在重要页面发布通知。同时，点点网保留在不事先通知用户的情况下中断或终止部分或全部服务的权利，对于因服务的中断或终止而造成的用户或第三方的任何损失，点点网不承担任何责任。
								</li>
								<li>
									用户同意尊重和维护点点网平台以及其他用户的合法权益。用户因违反有关法律、法规或协议规定中的任何条款而给点点网或任何第三方造成的损失，用户同意承担由此造成的一切损害赔偿责任。
								</li>
								<li>
									在适用法律允许的范围内，点点网保留对本协议任何条款的解释权和随时变更的权利。
									点点网可能会随时根据需要修改本协议的任一条款。如发生此类变更，点点网会提供新版本的条款。用户在变更后对点点网平台服务的使用将视为已完全接受变更后的条款。
								</li>
								<li>
									本协议在根据下述规定终止前，将会一直适用。当下列情况出现时，点点网可以随时中止与用户的协议：1)
									用户违反了本协议中的任何规定；2) 法律法规要求终止本协议;3) 点点网认为实际情形不再适宜继续执行本协议。
								</li>
								<li>
									本协议及因本协议产生的一切法律关系及纠纷，均适用中华人民共和国法律。用户与点点网在此同意以点点网营业所在地法院管辖。
								</li>
							</ol>
					</div>
		</div>
		
	</div>
</div>
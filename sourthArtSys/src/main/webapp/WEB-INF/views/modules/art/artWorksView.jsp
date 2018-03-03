<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>作品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function(){
			$('.carousel').carousel({
			  interval: false
			})
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/art/artworks/">作品列表</a></li>
		<li class="active"><a href="${ctx}/art/artworks/view?id=${artWorks.id}">作品查看</a></li>
	</ul>
	<div class="container" style="background: #efefef;">
		<!-- 标题 -->
		<div>
			<h4 style="text-align:center; color: #1d6ba2;font-size:20px;margin: 85px auto 0;letter-spacing:2px;">ISH Shanghai &amp; CIHE 上海供热展专题</h4>
			<p style="margin:22px auto 6px;text-align:right;font-size:14px;color:#333;overflow:hidden;font-weight:normal;padding-right:20px;font-family:宋体;">作者：浦实&nbsp;&nbsp;&nbsp;发布日期：2018/2/10</p>
		</div>
		<!-- 正文 -->
		<!-- 图文 -->
		<div style="margin: 10px;">
			<!-- 图片轮播 -->
			<div>
				<div id="myCarousel" class="carousel slide">
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class=""></li>
						<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="2" class=""></li>
					</ol>
					<div class="carousel-inner">
						<div class="item">
							<img style="height: 400px;width: 100%;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518278858899&di=9f446e6863843b15936de8c066abf9d0&imgtype=0&src=http%3A%2F%2Frescdn.qqmail.com%2Fdyimg%2F20131130%2F72E2908A2275.jpg" alt="">
							<div class="carousel-caption">
								<h4>First Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item active">
							<img style="height: 400px;width: 100%;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518278858902&di=259dcea7283bc8931d15490a1db26e1d&imgtype=0&src=http%3A%2F%2Fimg.ph.126.net%2FYbkrsEjRho5hyQGrY6idlQ%3D%3D%2F6598162681216308802.jpg" alt="">
							<div class="carousel-caption">
								<h4>Second Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
						<div class="item">
							<img style="height: 400px;width: 100%;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518278858901&di=a69d37bd21a0d1ccf88ec1e9aa9c87f0&imgtype=0&src=http%3A%2F%2Fwww.ywga.gov.cn%2Fjwgk%2Fsyzc%2F201703%2FW020170331331041430290.jpg" alt="">
							<div class="carousel-caption">
								<h4>Third Thumbnail label</h4>
								<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
					<a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
				</div>
			</div>
			<!-- 内容描述 -->
			<div>
				<div style="text-indent: 2em;font-size: 16px;">花儿，也可称其为“少年”，是广泛流传与我国西北民间的一种独特民歌。它历史悠久、源远流长、内容丰富、形式多样、曲调优美，具有浓郁的民族特色和高原风格，深受当地广大人民群众喜爱,花儿是一种民歌，它是以爱情为主要的表现对象，因此，“花儿”一词的形成，是爱情与实际生活相结合的必然结果。花儿又是一种民间的口头文学形式，是群众在生活当中不可缺少的艺术，是人民诗的源泉，它的形成有一个长期演变的过程，无数花儿歌手通过口头传唱，使这一民族艺术日益丰富。从本质上讲说，花儿是西北人按照自己方言的规律结合自己的审美心理而创作的，有一种泥土一样的纯朴；同时花儿也是民间美术的创作源泉，比如民间剪纸。我这里讲的是一位从花儿中走出来的回族剪纸艺人---井春霞。现实中，她已经不再拥有靓丽的容貌，已经步入中老年，她没有千种风情，万般浪漫，但是她没有因为失去青春而自卑，而是牢牢的把命运掌握在自己手里，让纸与刀行走在自己的世界里。让生活瞬间变得丰富多彩，为了回族剪纸默默的创造属于自己的一方天地。她的剪纸清丽脱俗，每一刀都恰到好处，细腻流畅丽。就像一个淡雅的女人，像秋叶般静美，淡淡地来，淡淡地去，也是一种经历过太多后的创作经历。作品呈现出把一切都看开的一份豁达，执着，不再对所有要求太多，只想做好自己。不忘初心简单的活剪纸的画卷里。淡淡的风，淡淡的云，伴随只是淡淡的梦，把一切都看淡就获得一份宁静。她用丰富的阅历，多年的沉淀和积累来提升自己的作品气场和格局。在创作过程过中，用一颗平和的心，不浮躁，一点一点地让画面充盈起来，让剪纸语言一点点丰满起来，沉稳地不断积攒知识，贮存内力，然后等待花开。花开的时候，依然不需要张扬，一层一层地开，一点一地香。花儿，使她的内心逐渐丰盈和淡然。
我喜欢静静的品她的作品，就如在香茗一杯幽香清醇，当我在默默地品味与蓦然回首的感悟中，真正体验到井春霞的作品有种淡淡的一丝乡音，柔柔的一缕心思，暖暖的一份真情，美美的一段剪纸梦。
				</div>
			</div>
			
			<!-- 评论 -->
			<div style="margin-top: 20px;">
				<div style="border-left: 6px solid #2fa4e7;"><h5>评论(50)</h5></div><br>
				<div style="width: 100%;">
					<textarea rows="4" style="width: 90%;resize: none;"></textarea>
					<button class="btn btn-danger">提交</button>
				</div>
				<div class="bs-docs-example">
            <div class="media">
              <a class="pull-left">
                <img class="media-object" alt="64x64" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg" style="width: 64px; height: 64px;">
              </a>
              <div class="media-body">
                <h5 class="media-heading">风一样的男人</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
              </div>
            </div>
            <div class="media">
              <a class="pull-left">
                <img class="media-object" alt="64x64" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg" style="width: 64px; height: 64px;">
              </a>
              <div class="media-body">
                <h5 class="media-heading">Media heading</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                <div class="media">
                  <a class="pull-left">
                    <img class="media-object" alt="64x64" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518282115434&di=29582735f97c3d570e0b3aa852fc06a0&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225215449_wiMQN.jpeg" style="width: 64px; height: 64px;">
                  </a>
                  <div class="media-body">
                    <h5 class="media-heading">Media heading</h5>
                    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                  </div>
                </div>
              </div>
            </div>
          </div>
			</div>
		</div>
	</div>
</body>
</html>
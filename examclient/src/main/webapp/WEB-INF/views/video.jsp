<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="common/header.jsp"%>
<video id="example_video_1" controls="controls" preload="none"
       style="margin: auto;" width="100%" height="100%"
       poster="resources/images/logo.png">
    <!-- <source src="resources/flash/oceans-clip.mp4" type='video/mp4'>
            <source src="resources/flash/1.flv" type='video/flv'> -->
    <source src="resources/flash/1.flv" type='video/flv'>
    <object type="application/x-shockwave-flash"
            data="resources/js/mediaelement/flashmediaelement.swf">
        <param name="movie" value="flashmediaelement.swf"/>
        Image as a last resort <img src="resources/images/logo.png"
                                    width="100%" height="100%" title="No video playback capabilities"/>
    </object>
</video>
<script>
    $('video,audio').mediaelementplayer(/* Options */);
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="form-holder text-center">
            <form action="/tube/upload" method="post">
                <div class="form-group">
                    <label for="titleInput">Title</label>
                    <input name="title" type="text" class="form-control" id="titleInput"
                           placeholder="title (Require)">
                </div>
                <div class="form-group">
                    <label for="authorInput">Author</label>
                    <input name="author" type="text" class="form-control" id="authorInput"
                           placeholder="author (Require)">
                </div>
                <img class="tips-img"
                     src="https://hc.weebly.com/hc/en-us/article_attachments/203583357/youtube_share.png">
                <div class="form-group">
                    <label for="youTubeLinkInput">YouTube Link
                        <a onmouseover="mouseOver(this)" onmouseout="mouseOut(this)"
                           style="text-decoration: none" href="#">Tips</a>
                    </label>
                    <input name="youTubeLink" type="text" class="form-control" id="youTubeLinkInput"
                           placeholder="https://youtu.be/21QDsw0XaFI (Require)">
                </div>
                <div class="form-group">
                    <label for="descriptionInput">Description</label>
                    <textarea name="description" class="form-control" id="descriptionInput"
                              cols="75" rows="4" placeholder="description... (Require)"></textarea>
                </div>
                <button class="btn btn-info" type="submit">Upload</button>
            </form>
        </div>
    </div>
</div>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="form-holder text-center">
            <form class="form-inline" action="/login" method="POST">
                <fieldset>
                    <div class="control-group">
                        <label class="control-label h3 mb-2" for="username">Username</label>
                        <div class="controls">
                            <input type="text" id="username" name="username" placeholder=""
                                   class="input-xlarge" onchange="hideError()">
                        </div>
                    </div>
                    <br/>
                    <div class="control-group">
                        <label class="control-label h3 mb-2" for="password">Password</label>
                        <div class="controls">
                            <input type="password" id="password" name="password" placeholder=""
                                   class="input-xlarge" onchange="hideError()">
                        </div>
                    </div>
                    <br/>
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-info">Login</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</html>
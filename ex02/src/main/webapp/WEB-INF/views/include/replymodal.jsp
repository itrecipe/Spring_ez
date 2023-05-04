<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>replymodal</title>
<meta charset="UTF-8">
<!-- RWD -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- MS -->
<meta http-equiv="X-UA-Compatible" content="IE=chrome"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,IE=EmulateIE9"/>

</head>
<body>

<div class="modal fade" id="myReplyModal" tabindex="-1" role="dialog"
	aria-lablledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times</button>
			<h3 class="modal-title" id="myModallabel">REPLY MODAL</h3>
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label>Reply</label>
				<input class="form-control" name='reply' value='New Reply!!!!'>
			</div>

			<div class="form-group">
				<label>Replyer</label>
				<input class="form-control" name='replyer' value='New Replyer'>
			</div>

			<div class="form-group">
				<label>Reply Date</label>
				<input class="form-control" name='replyDate' value='2023-05-04'>
			</div><!-- body -->
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-info">Close</button>
			</div><!-- footer -->
		</div> <!-- content -->
	</div> <!-- dialog -->
</div> <!-- modal fade -->
</body>
</html>
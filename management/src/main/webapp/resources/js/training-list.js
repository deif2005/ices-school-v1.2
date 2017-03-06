$(function() {
	question_list.initial();

});
/**
 * 删除章节方法
 * @param obj
 */
function delTrainingSection(sectionId,userId){
	var jsonData=new Object();
	jsonData.sectionId=sectionId;
	jsonData.userId=userId;
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : "admin/training/del-training-section",
		data:JSON.stringify(jsonData),
		success : function(message, tst, jqXHR) {
			if (!util.checkSessionOut(jqXHR))
				return false;
			if (message.result == "success") {
				util.success("删除成功", function(){
					window.location.reload();
				});
			} else {
				util.error("操作失败请稍后尝试:" + message.result);
			}

		},
		error : function(jqXHR, textStatus) {
			util.error("操作失败请稍后尝试");
		}
	});
}/**
 * 删除培训
 * @param obj
 */
function delTraining(trainingId){
	var jsonData=new Object();
	jsonData.trainingId=trainingId;
	jQuery.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "POST",
		url : "admin/training/del-training",
		data:JSON.stringify(jsonData),
		success : function(message, tst, jqXHR) {
			if (!util.checkSessionOut(jqXHR))
				return false;
			if (message.result == "success") {
				util.success("删除成功", function(){
					window.location.reload();
				});
			} else {
				util.error("操作失败请稍后尝试:" + message.result);
			}

		},
		error : function(jqXHR, textStatus) {
			util.error("操作失败请稍后尝试");
		}
	});
}


function uploadFile(obj) {
console.log(obj);
	var files = $('#uploadify1').prop('files');

	var data = new FormData();
	data.append('avatar', files[0]);

	var fileType=files[0].name.split('.')[1];
	console.log(fileType)
	if(fileType =='pdf' || fileType =='flv'|| fileType =='mp4'){
		$('#form-message').html('文件格式选择正确')
		$('#fileName').attr('value','');
		$('#fileType').attr('value','');
		$('#filePath').attr('value','');
	}else{
		$('#form-message').html('文件类型错误(请上传mp4 flv pdf文件)');
		return;
	}

	$.ajax({
		url: 'secure/upload-train-file?fileType='+fileType,
		type: 'POST',
		data: data,
		cache: false,
		processData: false,
		contentType: false,
		success : function(data) // 服务器成功响应处理函数
		{
			if(data.length >0){
				$('#form-message').html('上传成功');
				console.log(data);
				$('#fileName').attr('value',data[0]);
				$('#fileType').attr('value',data[2]);
				$('#filePath').attr('value',data[1]);
			}else{
				$('#form-message').html('服务器异常');
			}
		},
		error : function(data)// 服务器响应失败处理函数
		{
			$('#form-message').html('上传失败');
			console.log("服务器异常");
		}
	});

	return false;
}

var question_list = {
	initial : function initial() {
		this.bindDelete();
	},

	
	bindDelete : function bindDelete(){
		$(".delete-btn").click(function(){
			$.ajax({
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				type : "GET",
				url : "admin/common/tag-delete-" + $(this).data("id"),
				success : function(message, tst, jqXHR) {
					if (!util.checkSessionOut(jqXHR))
						return false;
					if (message.result == "success") {
						util.success("删除成功", function(){
							window.location.reload();
						});
					} else {
						util.error("操作失败请稍后尝试:" + message.result);
					}

				},
				error : function(jqXHR, textStatus) {
					util.error("操作失败请稍后尝试");
				}
			});

			return false;



		});
	}
};
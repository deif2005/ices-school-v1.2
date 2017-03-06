$(function() {
	exampaper_add.initial();
	$('#fileName').attr('value','');
});



//长传文件
function uploadFile(obj) {
	$('#fileName').attr('value','');
	//由于参数需要试卷类型，所以选择文件前得先选择试卷类型
	//var field=$('#exampaperType option').eq($('#exampaperType').get(0).selectedIndex).attr('value');
	var field=$('#exampaperType option:selected').attr('value');
	$('#exampaperType option:selected').attr('value')
	console.log(field)
	if(field == -1 || field == undefined){
		$(".add-update-exampaper-type .form-message").text("请选择试卷分类（专业）");
		//清空文件
		var wordFile=$('#wordFile');
		wordFile.after(wordFile.clone().val(""));
		wordFile.remove();
		return;
	}else{
		$(".add-update-exampaper-type .form-message").html("");
	}
	var files = $('#wordFile').prop('files');

	var data = new FormData();
	data.append('avatar', files[0]);

	var fileType=files[0].name.split('.')[1];
	console.log(fileType)
	if(fileType =='doc' || fileType == 'docx'){
		$('#form-message').html('文件格式选择正确');
	}else{
		$('#form-message').html('<span style="color:red">文件类型错误(请上传doc格式文件)</span>');
		return;
	}
console.log(data)
	$.ajax({
		url: util.getCurrentRole()+'/exampaper/upload-word-'+field+'/'+fileType,
		type: 'POST',
		data: data,
		cache: false,
		processData: false,
		contentType: false,
		success : function(data) // 服务器成功响应处理函数
		{
			if(data.result == 'success'){
				$('#form-message').html('上传成功');
				console.log(data);
				$('#fileName').attr('value',data.messageInfo);
			}else{
				$('#form-message').html('<span style="color: red">服务器异常</span>');
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

exampaper_add = {
		initial : function initial() {
			this.bindAddPoint();
			this.bindChangeAmount();
			this.bindChangeCreatExamPaperType();
			this.bindSubmit();
		},
		bindSubmit : function bindSubmit() {
			$("#form-exampaper-add").submit(function() {
				
				var verify_result = exampaper_add.verifyInput();
				var url;
				if($(".add-update-exampaper-creat-type select").val() == 3){
					url = util.getCurrentRole() + '/exampaper/paper-import';
				}else{
					url = util.getCurrentRole() + "/exampaper-add";
				}
				if (verify_result) {
					var question_entity = exampaper_add.composeEntity();
					$(".df-submit").attr("disabled","disabled");
					console.log(JSON.stringify(question_entity))
					$.ajax({
						headers : {
							'Accept' : 'application/json',
							'Content-Type' : 'application/json'
						},
						type : "POST",
						url : url,
						data : JSON.stringify(question_entity),
						success : function(message, tst, jqXHR) {
							if (!util.checkSessionOut(jqXHR))
								return false;
							if (message.result == "success") {
								if($(".add-update-exampaper-creat-type select").val() == 3){
									util.success("添加成功", function() {
										document.location.href = document.getElementsByTagName('base')[0].href + util.getCurrentRole() + '/exampaper/exampaper-edit/' + message.generatedId;
									});
									//window.open(message.messageInfo);
									console.log(message)
								}else{
									util.success("添加成功", function() {
										document.location.href = document.getElementsByTagName('base')[0].href + util.getCurrentRole() + '/exampaper/exampaper-edit/' + message.generatedId;
									});
								}

							} else {
								util.error("操作失败请稍后尝试:" + message.result);
								$(".df-submit").removeAttr("disabled");
							}

						},
						error : function(jqXHR, textStatus) {
							util.error("操作失败请稍后尝试");
							$(".df-submit").removeAttr("disabled");
						}
					});
				}

				return false;
			});
		},
		bindAddPoint : function bindAddPoint() {
			$("#add-point-btn").click(function() {
				var field = $("#field-select > option:selected");
				var point = $("#point-from-select > option:selected");
				if (field.length == 0 || point.length == 0) {
					util.error("请选择需要添加的知识点");
					return false;
				}

				var html = "<option value=\"" + point.attr("value") + "\">" + field.text() + " > " + point.text() + "</option>";
				var p = point.attr("value");
				if (!exampaper_add.checkPointDuplicate(p)) {
					util.error("不能重复添加");
					return false;
				}

				$("#point-to-select").append(html);
				return false;
			});

			$("#del-point-btn").click(function() {
				$("#point-to-select > option:selected").remove();
				return false;
			});

			$("#remove-all-point-btn").click(function() {
				$("#point-to-select").empty();
				return false;
			});
		},
		checkPointDuplicate : function checkPointDuplicate(p) {
			var points = $("#point-to-select option");
			for (var i = 0; i < points.length; i++) {
				var point = $(points[i]).attr("value");
				if (point == p)
					return false;
			}

			return true;
		},
		
		calculateTotalPoints : function(){
			var qt = $(".add-ques-type");
			var amount = 0;
			for(var i = 0 ; i< qt.length;i++){
				var itemamount = parseInt($(qt[i]).find(".add-ques-amount").val());
				var itemscore = parseFloat($(qt[i]).find(".add-ques-score").val());
				
				if(isNaN(itemamount)||isNaN(itemscore)){
					continue;
				}else{
					amount = amount +  itemamount * itemscore * 10;
				}
				
			}
			$(".add-total-point input").val(amount / 10);
			
			
		},
		
		bindChangeAmount : function(){
			$(".add-ques-amount").change(function(){
				exampaper_add.calculateTotalPoints();
			});
			$(".add-ques-score").change(function(){
				exampaper_add.calculateTotalPoints();
			});
		},
		/**
		 *组卷方式切换
		 */
		bindChangeCreatExamPaperType:function bindChangeCreatExamPaperType() {
			
			$(".add-update-exampaper-creat-type select").change(function(){
				if (1 == $(this).val()) {
					$(".add-update-types").hide();
					$(".add-update-exampaper-scope").hide();
					$(".add-total-point").hide();
					$("#fileDiv").hide();
				} else if (2 == $(this).val()) {
					$(".add-update-types").show();
					$(".add-update-exampaper-scope").show();
					$(".add-total-point").show();
					$("#fileDiv").hide();
				}else if (3 == $(this).val()) {
					$(".add-update-types").hide();
					$(".add-update-exampaper-scope").hide();
					$(".add-total-point").hide();
					$("#fileDiv").show();
				}
				
			});
			
		},
		/**
		 * 检查输入合法性
		 */
		verifyInput : function verifyInput() {
			$(".form-message").empty();
			$(".has-error").removeClass("has-error");
			var result = true;
			var r_checkName = exampaper_add.checkName();
			var r_checkTotalPoint = exampaper_add.checkTotalPoint();
			var r_checkPassPoint = exampaper_add.checkPassPoint();
			var r_checkDuration = exampaper_add.checkDuration();
			var r_checkKnowledge = exampaper_add.checkKnowledge();
			var r_checkType = exampaper_add.checkType();

			if($(".add-update-exampaper-creat-type select").val() == 2){
				result = r_checkName && r_checkTotalPoint && r_checkPassPoint && r_checkDuration && r_checkKnowledge && r_checkType;
			}else if($(".add-update-exampaper-creat-type select").val() == 1){
				result = r_checkName && r_checkPassPoint && r_checkDuration && r_checkType;
			}if($(".add-update-exampaper-creat-type select").val() == 3){
				result = r_checkName && r_checkPassPoint && r_checkDuration && r_checkType;
				//验证文件信息
				if(result){
					if(!$('#wordFile').prop('files')[0]){
						result=false;
						$('#form-message').html('<span style="color: red">请选择文件</span>');
					}
				}
			}
			
			
			
			return result;
		},
		
		checkName : function checkName() {
			var name = $(".add-update-exampapername input").val();
			if (name == "") {
				$(".add-update-exampapername .form-message").text("请输入试卷名称");
				$(".add-update-exampapername input").focus();
				$(".add-update-exampapername input").addClass("has-error");
				return false;
			} else if (name.length > 30) {
				$(".add-update-exampapername .form-message").text("内容过长，请保持在30个字符以内");
				$(".add-update-exampapername input").focus();
				$(".add-update-exampapername input").addClass("has-error");
				return false;
			} else {
				return true;
			}
		},
		
		checkTotalPoint : function checkTotalPoint(){
			var totall_point = $(".add-total-point input").val();
			if(exampaper_add.getType(totall_point)=="float"){
				$(".add-total-point .form-message").text("总分不能有小数");
				return false;
			}else if(isNaN(parseInt(totall_point))){
				$(".add-total-point .form-message").text("无效的值");
				return false;
			}
			return true;
		},
		checkDuration : function checkDuration() {
			var duration = $(".add-update-duration input").val();
			if (duration == "") {
				$(".add-update-duration .form-message").text("请输入考试时长（如：120）");
				return false;
			} else if (isNaN(duration)) {
				$(".add-update-duration .form-message").text("请输入数字");
				return false;
			} else if (!(duration > 10 && duration < 500)) {
				$(".add-update-duration .form-message").text("数字范围无效，考试的时长必须设置在10到500的范围内");
				return false;
			} else {
				return true;
			}
		},
		checkPassPoint : function checkPassPoint() {
			var totall_point = parseInt($(".add-total-point input").val());
			var point = parseInt($(".add-update-pass-point input").val());
			if (point == "") {
				$(".add-update-pass-point .form-message").text("请输入及格分数");
				return false;
			} else if (isNaN(point)) {
				$(".add-update-pass-point .form-message").text("请输入数字");
				return false;
			} else if (point > totall_point) {
				$(".add-update-pass-point .form-message").text("及格分数必须小于或等于总分数");
				return false;
			} else {
				return true;
			}
		},
		
		checkType : function checkType() {
			var fieldId = $(".add-update-exampaper-type select").val();
			if (fieldId == "-1") {
				$(".add-update-exampaper-type .form-message").text("请选择试卷分类（专业）");
				return false;
			} 
			
			return true;
		},
		
		checkKnowledge : function checkKnowledge(){
			var result = true;

			if ($("#point-to-select option").length == 0) {
				$(".add-update-exampaper-scope .form-message").text("至少选择一个知识点");
				$("#point-to-select").addClass("has-error");
				result = false;
			} else if ($("#point-to-select option").length > 10) {
				$(".add-update-exampaper-scope .form-message").text("知识点数量不应该超过10个");
				$("#point-to-select").addClass("has-error");
				result = false;
			}

			return result;
			
		},
		
		composeEntity : function composeEntity(){
			var paperParam = new Object();
			paperParam.paperName = $(".add-update-exampapername input").val();
			paperParam.passPoint = parseInt($(".add-update-pass-point input").val());
			paperParam.time = $(".add-update-duration input").val();
			paperParam.paperPoint = $("#total-point").val();
			paperParam.paperType = $(".add-update-exampaper-type select").val();
			
			var qt = $(".add-ques-type");
			var amountMap = new Object();
			var pointMap = new Object();
			for(var i = 0 ; i< qt.length;i++){
				var itemamount = parseInt($(qt[i]).find(".add-ques-amount").val());
				var itemscore = parseFloat($(qt[i]).find(".add-ques-score").val());
				var itemsid = $(qt[i]).find(".ques-id").val();
				if(isNaN(itemamount)||isNaN(itemscore)){
					continue;
				}else{
					amountMap[itemsid] = itemamount;
					pointMap[itemsid] = itemscore;
				}
			}
			if($(".add-update-exampaper-creat-type select").val() == 2){
				paperParam.questionTypeNum = amountMap;
				paperParam.questionTypePoint = pointMap;
				paperParam.paperPoint = $("#total-point").val();
			}else if($(".add-update-exampaper-creat-type select").val() == 1){
				paperParam.paperPoint = "100";
			}else if($(".add-update-exampaper-creat-type select").val() == 3){
				paperParam.paperPoint = "100";
				paperParam.fileName=$('#fileName').val();
				paperParam.fieldId=$('#exampaperType option:selected').attr('value');
			}
			
			
			
			var knowledges = $("#point-to-select option");
			var rateMap = new Object();
			knowledges.each(function(){
				rateMap[$(this).val()] = 0;
			});
			
			paperParam.questionKnowledgePointRate = rateMap;
			
			return paperParam;
		},
		
		getType : function getType(input) {
		    var m = (/[\d]+(\.[\d]+)?/).exec(input);
		    if (m) {
		       // Check if there is a decimal place
		       if (m[1]) { return 'float'; }
		       else { return 'int'; }          
		    }
		    return 'string';
		}
		

};















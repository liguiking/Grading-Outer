//test
({
	appDir:'./src/main/webapp',
	baseUrl:'./script',
	dir:'target/grading',
	modules:[{
		name:'main'
	}],
	fileExclusionRegExp:/^\.git/,
	optimizeCss:'standard',
	removeCombined:true,
	paths:{
		'jquery':'lib/jquery/jquery.min',
		'bootstrap':'lib/bootstrap/bootstrap.min'
	},
	shim:{
		'bootstap':{
			deps:['jquery']
		}
	}
});

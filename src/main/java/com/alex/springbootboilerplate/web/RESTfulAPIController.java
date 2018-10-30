package com.alex.springbootboilerplate.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

import com.alex.springbootboilerplate.entity.DemoInfo;
import com.alex.springbootboilerplate.service.DemoInfoService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;

@RestController
public class RESTfulAPIController {

	@Resource
	DemoInfoService service;

	/**
	 * GET（SELECT）：从服务器取出资源（一项或多项）; POST（CREATE）：在服务器新建一个资源;
	 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）; PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）;
	 * DELETE（DELETE）：从服务器删除资源。
	 */

	@RequestMapping(value = { "/demo" }, method = RequestMethod.POST)
	public List<DemoInfo> addDemoInfo(@ModelAttribute DemoInfo demo) {
		if (demo == null) {
			System.out.println("demo!");
		}
		// POST请求，用来创建DemoInfo
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		return service.insertDemoInfo(demo);
	}

	@RequestMapping(value = { "/demo-requestParam" }, method = RequestMethod.POST)
	public List<DemoInfo> insertDemoInfo(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("description") String description) {
		// POST请求，用来创建DemoInfo
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		DemoInfo demo = new DemoInfo();
		demo.setId(id);
		demo.setDescription(description);
		demo.setName(name);
		return service.insertDemoInfo(demo);
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.GET)
	public DemoInfo findDemoInfo(@PathVariable String id) {
		// GET请求，用来获取信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return service.findDemoInfo(id);
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.PUT)
	public List<DemoInfo> updateDemoInfo(@PathVariable String id) {
		// PUT请求，用来更新信息
		DemoInfo demo = new DemoInfo();
		demo.setId(id);
		demo.setDescription("我是第" + id + "ST");
		demo.setName("TS__" + id);
		return service.updateDemoInfo(demo);
	}

	@RequestMapping(value = "/demo/{id}", method = RequestMethod.DELETE)
	public List<DemoInfo> deleteDemoInfo(@PathVariable String id) {
		// DELETE请求，用来删除信息
		DemoInfo demo = new DemoInfo();
		demo.setId(id);
		return service.deleteDemoInfo(demo);
	}

	@RequestMapping(value = { "/demos", "/" }, method = RequestMethod.GET)
	public List<DemoInfo> findAll() {
		// GET请求，用来获取信息
		return service.findAll();
	}

	@GetMapping("/demov2")
	public void http2ServerPush(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PushBuilder pushBuilder = request.newPushBuilder();
		pushBuilder
				.path("/demo.png")
				.addHeader("content-type", "image/png")
				.push();

		try(PrintWriter respWriter = response.getWriter()){
			respWriter.write("<html>" +
					"<img src='/demo.png'>" +
					"</html>");
		}
	}

	@GetMapping(value = "/demo.png")
	public void download(HttpServletResponse response) throws IOException {
		//target/classes/
		InputStream data = getClass().getClassLoader().getResourceAsStream("demo.png");
		response.setHeader("content-type", "image/png");
		FileCopyUtils.copy(data, response.getOutputStream());
	}

	@GetMapping("/demov3")
	public void http2ServerPush3(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		PushBuilder pushBuilder = request.newPushBuilder();
//		pushBuilder
//				.addHeader("content-type", "text/event-stream")
//				.addHeader("cache-control", "no-cache")
//				.addHeader("connection", "keep-alive")
//				.addHeader("Access-Control-Allow-Origin", "*")
//				.addHeader("Access-Control-Expose-Headers", "*")
//				.addHeader("Access-Control-Allow-Credentials", "true")
//				.push();


		response.addHeader("Access-Control-Allow-Origin", "https://localhost:8099");
		response.addHeader("Access-Control-Expose-Headers", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("content-type", "text/event-stream, charset=UTF-8");
		response.addHeader("cache-control", "no-cache");
		response.addHeader("connection", "keep-alive");

		try(PrintWriter respWriter = response.getWriter()){
			respWriter.write("retry: 10000\n");
			respWriter.write("event: connecttime\n");
			respWriter.write("data: " + (new Date()).toString() + "\n\n");
			respWriter.write("data: " + (new Date()).toString() + "\n\n");
		}

	}
}

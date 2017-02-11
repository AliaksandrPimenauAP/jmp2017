package com.epam.jmp2017.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.jmp2017.constants.BaseConstants;
import com.epam.jmp2017.constants.WebConstants;
import com.epam.jmp2017.model.Action;
import com.epam.jmp2017.model.Data;
import com.epam.jmp2017.model.impl.Result;
import com.epam.jmp2017.model.impl.action.PrintIfBlackDog;
import com.epam.jmp2017.model.impl.action.PrintUpperCase;
import com.epam.jmp2017.util.DataFactory;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

//YAGNI
//Not overriding all the methods with differend implementations
@WebServlet(WebConstants.URL_PROCESS)
public class MainController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		process(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	//DRY
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(WebConstants.TYPE_CONTENT);
		String dataString = request.getParameter(BaseConstants.ATTR_DATA);
		JsonParser parser = new JsonParser();
		JsonReader reader = new JsonReader(new StringReader(dataString));
		List<Data> dataList = new ArrayList<>();
		reader.setLenient(true);
		if(!dataString.isEmpty()) {
			JsonElement inputElement = parser.parse(reader);
			if(inputElement != null) {
				Data data;
				if(inputElement.isJsonArray()) {
					JsonArray array = inputElement.getAsJsonArray();
					for (JsonElement el : array) {
						data = DataFactory.getData(el);
						if(data != null) {
							dataList.add(data);
						}
					}
				} else if(inputElement.isJsonObject()) {
					data = DataFactory.getData(inputElement);
					if (data != null) {
						dataList.add(data);
					}
				}
			}
		}

		PrintWriter out = response.getWriter();
		out.print(new Gson().toJson(performActions(dataList)));
	}

	private List<Result> performActions(List<Data> dataList) throws IOException {
		//soLid
		Action printUpperCase = new PrintUpperCase();
		Action printIfBlackDog = new PrintIfBlackDog();
		List<Result> results = new ArrayList<>();
		Result result;

		if(dataList != null) {
			//KISS
			dataList.sort(Comparator.comparingInt(Data::getTypeCode));
			for(Data data : dataList) {
				result = performAction(data,printUpperCase);
				if(result != null) {
					results.add(result);
				}

				result = performAction(data,printIfBlackDog);
				if(result != null) {
					results.add(result);
				}
			}
		}
		return results;
	}

	//soliD
	private Result performAction(Data data, Action action) {
		if (action.check(data)) {
			return new Result(action.perform(data));
		}
		return null;
	}
}

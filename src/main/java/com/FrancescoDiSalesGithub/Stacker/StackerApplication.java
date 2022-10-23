package com.FrancescoDiSalesGithub.Stacker;


import com.FrancescoDiSalesGithub.Stacker.model.BreakpointLine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StackerApplication {

	public static void main(String[] args)
	{
		System.out.println("--------------------------");
		System.out.println("Stacker ");
		System.out.println("--------------------------");

		String command = "";

		Stack<BreakpointLine> breakpointLineStack = new Stack<>();


		while(!command.equals("x"))
		{
			Scanner inputStream = new Scanner(System.in);
			System.out.println(">: ");
			command = inputStream.next();

			if(command.equals("a"))
			{
				BreakpointLine breakpointLineObject = new BreakpointLine();
				String objectName,lineNumber,instruction = "";
				Scanner localAddInput = new Scanner(System.in);

				System.out.println("insert object name: ");
				objectName = localAddInput.next();

				System.out.println("insert line number: ");
				lineNumber = localAddInput.next();

				System.out.println("insert instruction: ");
				instruction = localAddInput.next();

				breakpointLineObject.setObjectClass(objectName);
				breakpointLineObject.setLineNumber(lineNumber);
				breakpointLineObject.setInstruction(instruction);

				breakpointLineStack.push(breakpointLineObject);

			}

			if(command.equals("p"))
			{
				for(BreakpointLine item : breakpointLineStack)
				{
					System.out.println("class: " + item.getObjectClass()+" line: " + item.getLineNumber() + " instruction: " + item.getInstruction());
				}
			}

			if(command.equals("s"))
			{
				HashMap<String,List<BreakpointLine>> hashMap = new LinkedHashMap<>();
				List<BreakpointLine> listBreakPoint = new ArrayList<>();

				String filePath = "";

				System.out.println("insert path where do you want to save the json: ");
				Scanner fileInput = new Scanner(System.in);
				filePath = fileInput.next();


				int value = 0;
				for(BreakpointLine item : breakpointLineStack)
				{

					listBreakPoint.add(item);
					value++;
				}

				hashMap.put("stackCalls",listBreakPoint);

				try
				{
					FileOutputStream file = new FileOutputStream(filePath);

					ObjectMapper mapper = new ObjectMapper();
					String jsonStackObjectString = mapper.writeValueAsString(hashMap);
					file.write(jsonStackObjectString.getBytes(StandardCharsets.UTF_8));

					file.close();
				}
				catch (FileNotFoundException e)
				{
					throw new RuntimeException(e);
				}
				catch (IOException e)
				{
					throw new RuntimeException(e);
				}

			}

			if(command.equals("l"))
			{
				String filePath = "";
				Scanner fileInput = new Scanner(System.in);
				System.out.println("insert path where is located the json: ");
				filePath = fileInput.next();

				try
				{
					File file = new File(filePath);
					Scanner fileScanner = new Scanner(file);

					StringBuilder content = new StringBuilder();

					while(fileScanner.hasNext())
					{
						content.append(fileScanner.nextLine());
					}

					String jsonContent = content.toString();
					JSONObject jsonObject = new JSONObject(jsonContent);

					Object stackCalls = jsonObject.get("stackCalls");
					JSONArray stackCallsArray = (JSONArray) stackCalls;
					List<Object> listLines = stackCallsArray.toList();


					for(Object item : listLines)
					{
						HashMap<String,String> map = (HashMap<String, String>) item;
						BreakpointLine breakpointLineObject = new BreakpointLine();
						breakpointLineObject.setObjectClass(map.get("objectClass"));
						breakpointLineObject.setLineNumber(map.get("lineNumber"));
						breakpointLineObject.setInstruction(map.get("instruction"));

						breakpointLineStack.push(breakpointLineObject);
					}


				}
				catch(FileNotFoundException fe)
				{
					System.out.println("File not found");
				}
			}

			if(command.equals("h") || command.equals("help"))
			{
				System.out.println("a - adds a new breakpoint");
				System.out.println("s - saves the list as json");
				System.out.println("l - loades the list from a json file");
				System.out.println("p - prints the stack trace");
				System.out.println("d - deletes an item from the stack trace");
				System.out.println("x - exit from the program");
			}

			if(command.equals("d"))
			{
				int totalLines = 0;
				for(BreakpointLine item : breakpointLineStack)
				{

					System.out.println(totalLines + ") " +  "class: " + item.getObjectClass()+" line: " + item.getLineNumber() + " instruction: " + item.getInstruction());
					totalLines++;
				}

				System.out.println("which line do you want to remove?");
				Scanner deleteInput = new Scanner(System.in);
				int value = deleteInput.nextInt();

				breakpointLineStack.remove(value);
			}

			if(command.equals("x"))
			{

				System.out.println("goodbye");
			}

		}
	}

}

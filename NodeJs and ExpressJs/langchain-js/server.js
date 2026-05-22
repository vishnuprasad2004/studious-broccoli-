import { ChatOpenAI } from "@langchain/openai";
import * as dotenv from "dotenv";

dotenv.config();

const llm = new ChatOpenAI({
  apiKey: process.env.AI_API_KEY,
  model: "openai/gpt-oss-120b:free",
  configuration: {
    baseURL: "https://openrouter.ai/api/v1",
  },
});

const response = await llm.invoke([
  { role: "user", content: "Explain LangChainJS in 2 sentences." },
]);

console.log(response.content);

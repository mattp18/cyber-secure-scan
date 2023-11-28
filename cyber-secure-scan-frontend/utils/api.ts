import axios, { AxiosInstance } from "axios";

const API_BASE_URL = "http://localhost:8080/";

const axiosInstance: AxiosInstance = axios.create({
  baseURL: API_BASE_URL,
});

export const fetchData = async (endpoint: string) => {
  try {
    const response = await axiosInstance.get(endpoint);
    response.data;
  } catch (error) {
    console.log(error);
  }
};

export const uploadFile = async (file: File) => {
  const formData = new FormData();
  formData.append("file", file);

  try {
    const response = await axiosInstance.post("api/v1/scan/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    return response.data;
  } catch (error) {
    console.log("Error uploading file: " + error);
  }
};

"use client";

import { useRef, useState } from "react";
import { uploadFile } from "../../../utils/api";
import { AxiosResponse } from "axios";

interface ScanFile {
  scanFileResponse: any;
}

const ScanFile = () => {
  const [file, setFile] = useState<File | null>(null);
  const [showSuccessAlert, setShowSuccessAlert] = useState<boolean>(false);
  const [showFailureAlert, setShowFailureAlert] = useState<boolean>(false);
  const fileInputRef = useRef<HTMLInputElement | null>(null);
  const [scanResponseText, setScanResponseText] = useState<string | null>("");
  const isChecked = false;

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log("testing handle file change");
    const selectedFile = (event.target as HTMLInputElement).files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
    }
  };

  const hideSuccessAlert = () => {
    setShowSuccessAlert(false);
  };

  const hideFailureAlert = () => {
    setShowFailureAlert(false);
  };

  const onClickScanButton = async () => {
    console.log("testing again");
    if (file) {
      var returnedResponse = await uploadFile(file);
      console.log(returnedResponse);
      if (returnedResponse) {
        setScanResponseText(returnedResponse);
        console.log("testing response from request " + returnedResponse);
        const response = setFile(file);
        console.log(response);
        if (fileInputRef.current) {
          fileInputRef.current.value = "";
        }
        setFile(null);
        setShowSuccessAlert(true);
        setTimeout(hideSuccessAlert, 5000);
      } else {
        setShowFailureAlert(true);
        setTimeout(hideFailureAlert, 5000);
      }
      if (fileInputRef.current) {
        fileInputRef.current.value = "";
      }
    }
  };

  return (
    <div className="items-center flex flex-col">
      <input
        type="file"
        className="file-input file-input-bordered file-input-sm w-full max-w-xs mb-5"
        onChange={handleFileChange}
        ref={fileInputRef}
      />

      <button
        disabled={!file}
        onClick={onClickScanButton}
        className="btn btn-neutral mt-8 max-w-xs w-full"
      >
        Scan
      </button>

      {showSuccessAlert && (
        <div role="alert" className="alert alert-success mt-5">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="stroke-current shrink-0 h-6 w-6"
            fill="none"
            viewBox="0 0 24 24"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
            />
          </svg>
          <span>{scanResponseText}</span>
        </div>
      )}
      <div className="fixed top-4 right-4">
        {showFailureAlert && (
          <div role="alert" className="alert alert-error mt-0">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="stroke-current shrink-0 h-6 w-6"
              fill="none"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
            <span>An issue has occured.</span>
          </div>
        )}
      </div>
    </div>
  );
};

export default ScanFile;

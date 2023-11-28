"use client";

import { useRef, useState } from "react";
import { uploadFile } from "../../../utils/api";

const ScanFile = () => {
  const [file, setFile] = useState<File | null>(null);
  const [showSuccessAlert, setShowSuccessAlert] = useState<boolean>(false);
  const fileInputRef = useRef<HTMLInputElement | null>(null);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log("testing handle file change");
    const selectedFile = (event.target as HTMLInputElement).files?.[0];
    if (selectedFile) {
      setFile(selectedFile);
    }
  };

  const hideAlert = () => {
    setShowSuccessAlert(false);
  };

  const onClickScanButton = (event: React.MouseEvent<HTMLButtonElement>) => {
    console.log("testing again");
    if (file) {
      uploadFile(file);
      const response = setFile(file);
      console.log(response);
      if (fileInputRef.current) {
        fileInputRef.current.value = "";
      }
      setFile(null);
      setShowSuccessAlert(true);
      setTimeout(hideAlert, 5000);
    }
  };

  return (
    <div>
      <input
        type="file"
        className="file-input file-input-bordered file-input-sm w-full max-w-xs"
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
          <span>File has been scanned!</span>
        </div>
      )}
    </div>
  );
};

export default ScanFile;

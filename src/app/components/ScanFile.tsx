"use client";

import { useState } from "react";

const ScanFile = () => {
  const [file, setFile] = useState<File | null>(null);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log("testing handle file change");
    const selectedFile = (event.target as HTMLInputElement).files?.[0];
    if (selectedFile != null) {
      setFile(selectedFile);
    }
  };

  return (
    <div>
      <input
        type="file"
        className="file-input file-input-bordered file-input-sm w-full max-w-xs"
        onChange={handleFileChange}
        onClick={() => console.log("clicked")}
      />
      <button disabled={!file} className="btn btn-neutral mt-8 max-w-xs w-full">
        Scan
      </button>
    </div>
  );
};

export default ScanFile;

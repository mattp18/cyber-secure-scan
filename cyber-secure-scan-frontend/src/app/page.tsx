import ScanFile from "./components/ScanFile";
import Navbar from "./components/Navbar";
import { FaGithubSquare } from "react-icons/fa";
import Footer from "./components/Footer";

export default function Home() {
  return (
    <main className="max-w-8xl mx-auto mt-4">
      <Navbar />
      <div className="text-center items-center my-5 flex flex-col gap-4">
        <h1 className="text-5xl font-bold mb-5">Cyber Secure Scan</h1>
        <a href="https://github.com/mattp18">
          <FaGithubSquare size={50} />
        </a>
        {/* <h1 className="text-sm mb-8">
          This website is intended for general information and non-sensitive
          data sharing purposes only. We do not collect, store, or process
          sensitive personal information on this platform. Please refrain from
          uploading any data that includes, but is not limited to: Social
          Security numbers Financial information Passwords or login credentials
          Health-related information Confidential business data The security of
          your data is our top priority; however, no online platform is entirely
          free from risks. Be cautious about the type of information you choose
          to share, and avoid uploading anything sensitive. By using this
          website, you acknowledge and agree that the responsibility for
          protecting sensitive information lies with you. We shall not be held
          liable for any unintended disclosure or misuse of sensitive data. If
          you have any concerns or questions regarding data security, please
          contact us at cybersecurescan@gmail.com Thank you for your
          understanding and cooperation.
        </h1> */}
        <div className="collapse bg-base-100">
          <input type="checkbox" />
          <div className="collapse-title text-xs font-medium underline">
            Privacy Notice and Disclaimer
          </div>
          <div className="collapse-content">
            <p className="max-w-sm mx-auto text-sm text-center p-1 bg-white rounded shadow">
              This website is intended for general information and non-sensitive
              data sharing purposes only. We do not collect, store, or process
              sensitive personal information on this platform. Please refrain
              from uploading any data that includes, but is not limited to:
              Social Security numbers Financial information Passwords or login
              credentials Health-related information Confidential business data
              The security of your data is our top priority; however, no online
              platform is entirely free from risks. Be cautious about the type
              of information you choose to share, and avoid uploading anything
              sensitive. By using this website, you acknowledge and agree that
              the responsibility for protecting sensitive information lies with
              you. We shall not be held liable for any unintended disclosure or
              misuse of sensitive data. If you have any concerns or questions
              regarding data security, please contact us at
              cybersecurescan@gmail.com Thank you for your understanding and
              cooperation.
            </p>
          </div>
        </div>
        <ScanFile />
        <Footer />
      </div>
    </main>
  );
}

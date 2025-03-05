const uploadDiv = document.querySelector("#upload");
if (uploadDiv) uploadDiv.style.display = "none"; // Initially hidden
const uploadBtn = document.querySelector("#uploadBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const input = document.querySelector("#uploadFile");

const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1)) || '';
console.log("ctx=", contextPath);

// File upload handling
if (input) {
    input.addEventListener("change", async () => {
        const [file] = input.files || [];
        const { name: fName, size: fSize } = file || {};

        if (!fName) return;

        console.log(`fileName = ${fName}`);
        console.log(`fileSize = ${fSize}`);

        const fileForm = /\.(jpg|jpeg|png|gif)$/i;
        const maxSize = 5 * 1024 * 1024; // 5MB

        if (!fileForm.test(fName)) {
            alert("오직 이미지 파일만 업로드 가능합니다");
            return;
        }

        if (fSize > maxSize) {
            alert("파일 크기 5MB 이하만 가능합니다");
            return;
        }

        const form = document.querySelector("#imgForm");
        if (!form) {
            console.error("Form #imgForm not found");
            alert("업로드 폼을 찾을 수 없습니다");
            return;
        }

        const formData = new FormData(form);

        try {
            const response = await fetch(`${contextPath}/memberUploadImg.do`, {
                method: 'POST',
                body: formData,
            });

            if (!response.ok) {
                throw new Error(`Upload failed : ${response.status}`);
            }

            const data = await response.text();

            if (data === 'fail') {
                alert('이미지 저장 실패');
                return;
            }

            alert('이미지 업로드 성공');
            const photo = document.querySelector('#photo');
            if (photo) {
                photo.src = `/Uploads/${data}`;
                photo.classList.remove("default");
            } else {
                console.error("Photo element not found");
            }
            input.value = ''; // Reset input
        } catch (error) {
            console.error('Upload error:', error);
            alert('업로드 중 오류 발생: ' + error.message);
        }
    });
}

// Toggle upload div visibility
if (uploadBtn) {
    uploadBtn.addEventListener("click", () => {
        console.log("업로드 버튼 클릭");
        if (uploadDiv) {
            uploadDiv.style.display = uploadDiv.style.display === "none" ? "block" : "none";
        } else {
            console.error("Upload div not found");
        }
    });
}

// Delete image handling
if (deleteBtn) {
    deleteBtn.addEventListener("click", async () => {
        console.log("삭제 버튼 클릭");
        const photo = document.querySelector('#photo');
    
        if (photo.classList.contains('default')) {
            alert('기본 이미지는 삭제 불가');
            return null;
        }

        const numElement = document.querySelector('#num');
        if (!numElement) {
            alert("회원 번호를 찾을 수 없습니다");
            return null;
        }

        const num = numElement.value;

        try {
            const response = await fetch(`${contextPath}/memberDeleteImg.do`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
                },
                body: `num=${encodeURIComponent(num)}`,
            });

            if (!response.ok) {
                throw new Error(`Delete failed with status: ${response.status}`);
            }

            const data = await response.text();

            if (data === 'fail') {
                alert('이미지 삭제 실패');
                return null;
            }

            alert('이미지 삭제 성공');
            photo.src = `${contextPath}/images/default.jpg`;
            photo.classList.add("default");
			location.reload(true);
        } catch (error) {
            console.error('Delete error:', error);
            alert('삭제 중 오류 발생: ' + error.message);
        }
    });
}
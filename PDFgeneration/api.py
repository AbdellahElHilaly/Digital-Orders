from flask import Flask, request, send_file
from fpdf import FPDF
import json

app = Flask(__name__)
@app.route('/generate_pdf', methods=['POST'])
def generate_pdf():
    try:
        data = request.json  # Get JSON data from request
        pdf = FPDF()
        pdf.add_page()
        pdf.set_font("Arial", size=12)

        # Add a logo to the PDF
        pdf.image('logo.png', x=150, y=10, w=30)  # Change path and dimensions

        # Iterate through JSON data and add to PDF
        for key, value in data.items():
            pdf.cell(200, 10, txt=f"{key}: {value}", ln=True)

        pdf_output = 'output.pdf'
        pdf.output(pdf_output)

        return send_file(pdf_output, as_attachment=True)

    except Exception as e:
        return str(e), 500

if __name__ == '__main__':
    app.run(debug=True)


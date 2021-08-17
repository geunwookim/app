from PIL import ImageGrab
import cv2
import numpy as np

from pytesseract import *


while True:
    global image
    image = cv2.cvtColor(np.array(ImageGrab.grab(bbox=(1650, 213, 1759, 271))), cv2.COLOR_BGR2RGB)
    cv2.imshow("image", image)
    key = cv2.waitKey(100)
    break

cv2.imwrite('C:/Users/computer_name/Desktop/test/image.png', image)

cv2.destroyAllWindows()

pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

img = cv2.imread("C:/Users/computer_name/Desktop/test/image.png")

hsv = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

#cv2.imshow('Image', img)

text = pytesseract.image_to_string(img,config='--psm 6')

print(text[0:4])
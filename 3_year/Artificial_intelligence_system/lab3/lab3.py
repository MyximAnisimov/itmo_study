import pandas as pd
import numpy as np
import math
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split

data = pd.read_csv("california_housing_train.csv")

num_rows = data.shape[0]
num_columns = data.shape[1]
print("Общее количество строк в датасете:", num_rows)
print("Общее количество столбцов в датасете:", num_columns)

X = data.drop(columns=["median_house_value"])
y = data["median_house_value"]

rows_count = X.shape[0]
cols_count = X.shape[1]

means = data.mean()
corrected_dispersions = np.sum(np.power(data - means, 2)) / (rows_count - 1)
standart_offsets = np.sqrt(corrected_dispersions)
mins = data.min()
maxs = data.max()
# visualization

# правило стёрджеса
bins_count = 1 + int(math.log(rows_count, 2))

data_from = X.join(y)
titles = data_from.columns
fig = plt.figure(figsize=(12, 12))

for i, column in enumerate(titles):
  ax = fig.add_subplot(3, 3, i + 1)

  hist = ax.hist(data_from[column], bins = bins_count, edgecolor = "black")

  ax.plot([means[i], means[i]], [0, np.max(hist[0])], color = "red", linestyle='--')
  ax.plot([means[i] - standart_offsets[i], means[i] - standart_offsets[i]], [0, np.max(hist[0])], color = "orange", linestyle='--')
  ax.plot([means[i] + standart_offsets[i], means[i] + standart_offsets[i]], [0, np.max(hist[0])], color = "orange", linestyle='--')

  ax.set_title(titles[i], fontsize=16)

fig.tight_layout()
plt.show()

#вычисление квантилей, средних значений и пр.
means = data.mean()
print("Средние значения для каждого столбца:")
print(means)

std_deviation = data.std()
print("\nСтандартное отклонение для каждого столбца:")
print(std_deviation)

min_values = data.min()
max_values = data.max()
print("\nМинимальные значения для каждого столбца:")
print(min_values)
print("\nМаксимальные значения для каждого столбца:")
print(max_values)

quantiles = data.quantile([0.25, 0.50, 0.75])
print("\nКвантили для каждого столбца:")
print(quantiles)

# Обработка отсутствующих значений
data = data.dropna()

# Разделение признаков на числовые и категориальные
data = pd.get_dummies(data, drop_first=True)

# Предобработка числовых признаков
scaler = StandardScaler() # для нормализации (центрирует данные и масштабирует)
data_scaled = data.copy()
data_scaled.iloc[:, :-1] = scaler.fit_transform(data_scaled.iloc[:, :-1])

X = data_scaled.drop(columns=['median_house_value'])  # искл. столбец (ц.п.)
y = data_scaled['median_house_value']  # цп

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.1, random_state=20)

def least_squares(X, y):
    X_b = np.c_[np.ones((X.shape[0], 1)), X]
    beta_best = np.linalg.inv(X_b.T.dot(X_b)).dot(X_b.T).dot(y)
    return beta_best

def evaluate_model(beta, X, y):
    X_b = np.c_[np.ones((X.shape[0], 1)), X]
    y_pred = X_b.dot(beta)
    r2 = 1 - np.sum((y - y_pred) ** 2) / np.sum((y - np.mean(y)) ** 2)
    return r2
X_train_model1 = X_train
X_test_model1 = X_test

beta_model1 = least_squares(X_train_model1.values, y_train.values)

r2_model1 = evaluate_model(beta_model1, X_test_model1.values, y_test)
print(f"R^2 для модели 1: {r2_model1}")

X_train_model2 = X_train.copy().drop(["longitude", "latitude"], axis=1)
X_test_model2 = X_test.copy().drop(["longitude", "latitude"], axis=1)

beta_model2 = least_squares(X_train_model2.values, y_train.values)

r2_model2 = evaluate_model(beta_model2, X_test_model2.values, y_test)
print(f"R^2 для модели 2: {r2_model2}")

X_train_model3 = X_train.copy()
X_test_model3 = X_test.copy()

X_train_model3["income_per_household"] = X_train_model3["median_income"] * X_train_model3["households"]
X_test_model3["income_per_household"] = X_test_model3["median_income"] * X_test_model3["households"]

beta_model3 = least_squares(X_train_model3.values, y_train.values)

r2_model3 = evaluate_model(beta_model3, X_test_model3.values, y_test)
print(f"R^2 для модели 3: {r2_model3}")



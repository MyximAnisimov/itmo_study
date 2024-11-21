import pandas as pd
import numpy as np
import random
import math
import matplotlib.pyplot as plt

data = pd.read_csv("WineDataset.csv")
data.describe()

if not data.isna().any().any():
    print("В данных нет пустых значений.")
else:
    data.dropna(inplace=True)
    print("Пустые значения был удалены.")

# Категориальных признаков нет, поэтому ничего с ними не делаем

# Стандартизация (масштабирование) признаков
from sklearn.preprocessing import StandardScaler

scaler = StandardScaler()
X = data.drop("Wine", axis=1)
X = pd.DataFrame(scaler.fit_transform(X), columns=X.columns)
y = data["Wine"]

rows_count = data.shape[0]  # количество строк и столбцов
cols_count = data.shape[1]
# Вычисление ср.зн., дисперция, станд. откл., мин и маx
means = data.mean()
corrected_dispersions = np.sum(np.power(data - means, 2)) / (rows_count - 1)
standart_offsets = np.sqrt(corrected_dispersions)
mins = data.min()
maxs = data.max()

bins_count = 1 + int(math.log(rows_count, 2))

titles = data.columns
fig = plt.figure(figsize=(9, 9))

for i, column in enumerate(titles):
    ax = fig.add_subplot(4, 4, i + 1)
    hist = ax.hist(data[column], bins=bins_count, edgecolor="black")
    # линии для отображения среднего значения и стандартных отклонений
    ax.plot([means[i], means[i]], [0, np.max(hist[0])], color="red")
    ax.plot([means[i] - standart_offsets[i], means[i] - standart_offsets[i]], [0, np.max(hist[0])], color="yellow")
    ax.plot([means[i] + standart_offsets[i], means[i] + standart_offsets[i]], [0, np.max(hist[0])], color="yellow")

    ax.set_title(titles[i], fontsize=16)

fig.tight_layout()
plt.show()

X = data.drop(columns=["Wine"])  # X содержит все столбцы данных, кроме "Wine"
y = data["Wine"]  # y содержит столбец "Wine"
# Нормализация
X = (X - X.mean()) / (X.max() - X.min())

# Выбор 3 случайных столбцов после нормализации
r_columns = list(X.columns)
random.shuffle(r_columns)
r_columns = r_columns[:3]

colors = {1: "red", 2: "green", 3: "blue"}
df = X[r_columns].join(y)
# выбранные столбцы с целевой переменной
print(df.columns)
ax = plt.axes(projection="3d")

for i in list(set(y)):
    values = df.loc[df["Wine"] == i]
    ax.scatter(values[df.columns[0]], values[df.columns[1]], values[df.columns[2]], color=colors[i])

plt.show()

# Преобразование данных в матрицу и вычисление ковариационной матрицы
X_mat = np.asarray(X)
cov = np.matmul(X_mat.T, X_mat)
# Вычисление собственных значений и собственных векторов
eig_vals, eig_vectors = np.linalg.eig(cov)
eig_vectors = eig_vectors.T
# сортировка в порядке убывания
eig = [[eig_vals[i], eig_vectors[i]] for i in range(len(eig_vals))]
eig.sort(key = lambda t: t[0], reverse = True)
# первые три вектора
pca = [eig[i][1] for i in range(3)]
X_reduced = np.matmul(pca, X_mat.T).T
X_reduced = pd.DataFrame(X_reduced, columns=["pca1", "pca2", "pca3"])
# Объединение преобразованных данных с целевой переменной
df = X_reduced.join(y)

print(df.columns)

fig = plt.figure(figsize=(20, 20))

ax = fig.add_subplot(1, 3, 2, projection="3d")

for i in list(set(y)):
  values = df.loc[df["Wine"] == i]
  ax.scatter(values[df.columns[0]], values[df.columns[1]], values[df.columns[2]], color = colors[i])

ax.view_init(-140, 60)

plt.show()

def euclidean_distance(x1, x2):
    return np.sqrt(np.sum((x1 - x2) ** 2))

# X:               признаки
# y:               метки классов
# query_point:     точка для которой выполняется классификация
# k:               количество ближайших соседей, которые будут учтены
def k_nearest_neighbors(X, y, query_point, k):
    # Вычисляем расстояние между точками в многомерном пространстве
    distances = [euclidean_distance(query_point, x) for x in X]

    # Получаем точки с наименьшими расстояниями
    k_indices = np.argsort(distances)[:k]
    k_nearest_labels = [y[i] for i in k_indices]
    # Берем самые часто встречающиеся
    most_common = np.bincount(k_nearest_labels).argmax()

    return most_common


from sklearn.model_selection import train_test_split

np.random.seed(42)  # seed для генератора случайных чисел, для обеспечения воспроизводимости результатов.


# Функция для получения матриц ошибок
def get_confusion_matrices(k_values, X_model, y):
    confusion_matrices_model = []
    X_train, X_test, y_train, y_test = train_test_split(X_model, y, test_size=0.2, random_state=42)
    # Для каждой точки в тестовом наборе выполняется классификация
    for k in k_values:
        y_pred_model = [k_nearest_neighbors(X_train.values, y_train.values, x, k) for x in X_test.values]
        confusion_matrix = np.zeros((3, 3), dtype=int)
        # Строится матрица ошибок, которая считает, сколько точек было правильно и неправильно классифицировано для каждого класса
        for i in range(len(y_test)):
            confusion_matrix[y_test.iloc[i] - 1][y_pred_model[i] - 1] += 1
            confusion_matrices_model.append(confusion_matrix)

    return confusion_matrices_model

k_values = [3, 5, 10]

# Модель 1
random_feature_indices = np.random.choice(X.shape[1], size=3, replace=False)
X_model1 = X.iloc[:, random_feature_indices]

# Модель 2
fixed_feature_indices = [0, 1, 5]
X_model2 = X.iloc[:, fixed_feature_indices]
# Выбираются случайные признаков из матрицы признаков X.
confusion_matrices_model1 = get_confusion_matrices(k_values, X_model1, y)
# Фиксированный набор признаков
confusion_matrices_model2 = get_confusion_matrices(k_values, X_model2, y)

# Вывод матриц ошибок
for k, confusion_matrix in zip(k_values, confusion_matrices_model1):
    print(f"Матрицы ошибок для Модели 1 с k={k}:\n", confusion_matrix)

print("\n---------------------------------\n")

for k, confusion_matrix in zip(k_values, confusion_matrices_model2):
    print(f"Матрицы ошибок для Модели 2 с k={k}:\n", confusion_matrix)